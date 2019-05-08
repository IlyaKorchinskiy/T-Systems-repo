package ru.korchinskiy.dao.impl;

import org.springframework.stereotype.Repository;
import ru.korchinskiy.dao.UserStatsDAO;
import ru.korchinskiy.entity.UserStats;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Calendar;
import java.util.List;

@Repository
public class UserStatsDAOImpl implements UserStatsDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public UserStats getUserStatsByUserIdAndDate(Long userId, Calendar calendar) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserStats> query = builder.createQuery(UserStats.class);
        Root<UserStats> root = query.from(UserStats.class);
        query.select(root).where(builder.equal(root.get("user").get("id"), userId),
                builder.equal(root.get("month"), calendar.get(Calendar.MONTH) + 1),
                builder.equal(root.get("year"), calendar.get(Calendar.YEAR)));
        List<UserStats> userStatsList = entityManager.createQuery(query).getResultList();
        if (userStatsList.size() == 0) return null;
        return userStatsList.get(0);
    }

    @Override
    public List<UserStats> getTopTenUserStatsBySum(Integer month, Integer year) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserStats> query = builder.createQuery(UserStats.class);
        Root<UserStats> root = query.from(UserStats.class);
        query.select(root).where(builder.equal(root.get("month"), month),
                builder.equal(root.get("year"), year));
        query.orderBy(builder.desc(root.get("sum")));
        List<UserStats> userStatsList = entityManager.createQuery(query).getResultList();
        if (userStatsList.size() > 9) userStatsList = userStatsList.subList(0, 10);
        return userStatsList;
    }

    @Override
    public Double getRevenueByMonth(Integer month, Integer year) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Double> query = builder.createQuery(Double.class);
        Root<UserStats> root = query.from(UserStats.class);
        query.select(builder.sum(root.get("sum").as(Double.class)))
                .where(builder.equal(root.get("month"), month),
                        builder.equal(root.get("year"), year));
        TypedQuery<Double> typedQuery = entityManager.createQuery(query);
        return typedQuery.getSingleResult();
    }

    @Override
    public void saveUserStats(UserStats userStats) {
        this.entityManager.persist(userStats);
    }
}

package ru.korchinskiy.dao.impl;

import org.springframework.stereotype.Repository;
import ru.korchinskiy.dao.ProductStatsDAO;
import ru.korchinskiy.entity.ProductStats;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Calendar;
import java.util.List;

@Repository
public class ProductStatsDAOImpl implements ProductStatsDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ProductStats getProductStatsByProductIdAndDate(Long id, Calendar calendar) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ProductStats> query = builder.createQuery(ProductStats.class);
        Root<ProductStats> root = query.from(ProductStats.class);
        query.select(root).where(builder.equal(root.get("product").get("id"), id),
                builder.equal(root.get("month"), calendar.get(Calendar.MONTH) + 1),
                builder.equal(root.get("year"), calendar.get(Calendar.YEAR)));
        List<ProductStats> productStatsList = entityManager.createQuery(query).getResultList();
        if (productStatsList.size() == 0) return null;
        return productStatsList.get(0);
    }

    @Override
    public List<ProductStats> getTopTenProductStatsByAmount(Integer month, Integer year) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ProductStats> query = builder.createQuery(ProductStats.class);
        Root<ProductStats> root = query.from(ProductStats.class);
        query.select(root).where(builder.equal(root.get("month"), month),
                builder.equal(root.get("year"), year));
        query.orderBy(builder.desc(root.get("amount")));
        List<ProductStats> productStatsList = entityManager.createQuery(query).getResultList();
        if (productStatsList.size() > 9) productStatsList = productStatsList.subList(0, 10);
        return productStatsList;
    }

    @Override
    public void saveProductStats(ProductStats productStats) {
        this.entityManager.persist(productStats);
    }

}

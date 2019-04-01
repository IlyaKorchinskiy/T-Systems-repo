package ru.korchinskiy.dao;

import ru.korchinskiy.entity.UserStats;

import java.util.Calendar;
import java.util.List;

public interface UserStatsDAO {
    UserStats getUserStatsByUserIdAndDate(Long userId, Calendar calendar);

    List<UserStats> getTopTenUserStatsBySum(Integer month, Integer year);

    Double getRevenueByMonth(Integer month, Integer year);

    void saveUserStats(UserStats userStats);
}

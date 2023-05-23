package ru.coda.traineeship.statistics.service.abstracts;

import ru.coda.traineeship.statistics.util.StatisticCount;

import java.util.List;

public interface StatisticsService {
    Integer responseCount();
    List<StatisticCount> getStatic(String what);

    List<StatisticCount> ageStatic();

    List<StatisticCount> cityStatic();

    List<StatisticCount> universityStatic();

    List<StatisticCount> educationStatic();

    List<StatisticCount> experienceStatic();

    List<StatisticCount> directionsStatic();

    List<StatisticCount> channelStatic();

}

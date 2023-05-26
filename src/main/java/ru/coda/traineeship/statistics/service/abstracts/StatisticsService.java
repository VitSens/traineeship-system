package ru.coda.traineeship.statistics.service.abstracts;

import ru.coda.traineeship.statistics.util.StatisticCount;

import java.util.List;

public interface StatisticsService {
    Integer responseCount();
    List<StatisticCount> getStaticForCandidate(String what);
    List<StatisticCount> getStaticForTrainee(String what);
    List<StatisticCount> responseForCandidateStatic();
    List<StatisticCount> ageStatic();

    List<StatisticCount> cityStatic();

    List<StatisticCount> universityStatic();

    List<StatisticCount> educationForCandidateStatic();

    List<StatisticCount> experienceStatic();

    List<StatisticCount> directionsStatic();

    List<StatisticCount> channelStatic();

    List<StatisticCount> educationForTraineeStatic();

    List<StatisticCount> taskForTraineeStatic();

    List<StatisticCount> responseForTraineeStatic();

}

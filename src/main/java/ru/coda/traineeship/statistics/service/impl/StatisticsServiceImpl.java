package ru.coda.traineeship.statistics.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ru.coda.traineeship.statistics.repository.StatisticsRepository;
import ru.coda.traineeship.statistics.service.abstracts.StatisticsService;
import ru.coda.traineeship.statistics.util.StatisticCount;

import java.util.List;

public class StatisticsServiceImpl implements StatisticsService {

    private StatisticsRepository statisticsRepository;

    @Autowired
    public StatisticsServiceImpl(StatisticsRepository statisticsRepository) {
        this.statisticsRepository = statisticsRepository;
    }

    @Override
    public Integer responseCount() {
        return statisticsRepository.findAll().size();
    }

    @Override
    public List<StatisticCount> ageStatic() {
        return statisticsRepository.countAge();
    }

    @Override
    public List<StatisticCount> cityStatic() {
        return statisticsRepository.countCity();
    }

    @Override
    public List<StatisticCount> universityStatic() {
        return statisticsRepository.countUniversity();
    }

    @Override
    public List<StatisticCount> educationStatic() {
        return statisticsRepository.countEducation();
    }

    @Override
    public List<StatisticCount> experienceStatic() {
        return statisticsRepository.countExperience();
    }

    @Override
    public List<StatisticCount> directionsStatic() {
        return statisticsRepository.countDirections();
    }

    @Override
    public List<StatisticCount> channelStatic() {
        return statisticsRepository.countChannel();
    }
}

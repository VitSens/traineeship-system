package ru.coda.traineeship.statistics.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.coda.traineeship.statistics.repository.StatisticsRepository;
import ru.coda.traineeship.statistics.service.abstracts.StatisticsService;
import ru.coda.traineeship.statistics.util.StatisticCount;

import java.util.List;
@Service("StatisticsService")
@Transactional
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
    public List<StatisticCount> getStatic(String what) {
        switch (what) {
            case ("age"):
                return ageStatic();
            case ("city"):
                return cityStatic();
            case ("university"):
                return universityStatic();
            case ("education"):
                return educationStatic();
            case ("directions"):
                return directionsStatic();
            case ("experience"):
                return experienceStatic();
            case ("channel"):
                return channelStatic();
            default:
                return null;
        }
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

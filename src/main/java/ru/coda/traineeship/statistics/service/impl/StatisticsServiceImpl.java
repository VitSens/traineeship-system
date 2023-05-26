package ru.coda.traineeship.statistics.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.coda.traineeship.statistics.repository.StatisticsCandidateRepository;
import ru.coda.traineeship.statistics.repository.StatisticsTraineeRepository;
import ru.coda.traineeship.statistics.service.abstracts.StatisticsService;
import ru.coda.traineeship.statistics.util.StatisticCount;

import java.util.List;
@Service("StatisticsService")
@Transactional
public class StatisticsServiceImpl implements StatisticsService {

    private StatisticsCandidateRepository statisticsCandidateRepository;
    private StatisticsTraineeRepository statisticsTraineeRepository;

    @Autowired
    public StatisticsServiceImpl(StatisticsCandidateRepository statisticsCandidateRepository, StatisticsTraineeRepository statisticsTraineeRepository) {
        this.statisticsCandidateRepository = statisticsCandidateRepository;
        this.statisticsTraineeRepository = statisticsTraineeRepository;
    }

    @Override
    public List<StatisticCount> getStaticForCandidate(String what) {
        switch (what) {
            case ("response"):
                return responseForCandidateStatic();
            case ("age"):
                return ageStatic();
            case ("city"):
                return cityStatic();
            case ("university"):
                return universityStatic();
            case ("education"):
                return educationForCandidateStatic();
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
    public List<StatisticCount> getStaticForTrainee(String what) {
        switch (what) {
            case ("response"):
                return responseForTraineeStatic();
            case ("task"):
                return taskForTraineeStatic();
            case ("education"):
                return educationForTraineeStatic();
            default:
                return null;
        }
    }

    @Override
    public Integer responseCount() {
        return statisticsCandidateRepository.findAll().size();
    }

    @Override
    public List<StatisticCount> educationForTraineeStatic() {
        return statisticsTraineeRepository.countEducation();
    }

    @Override
    public List<StatisticCount> taskForTraineeStatic() {
        return statisticsTraineeRepository.countTask();
    }

    @Override
    public List<StatisticCount> responseForTraineeStatic() {
        return statisticsTraineeRepository.countResponse();
    }

    @Override
    public List<StatisticCount> ageStatic() {
        return statisticsCandidateRepository.countAge();
    }
    @Override
    public List<StatisticCount> responseForCandidateStatic() {
        return statisticsCandidateRepository.countResponse();
    }
    @Override
    public List<StatisticCount> cityStatic() {
        return statisticsCandidateRepository.countCity();
    }

    @Override
    public List<StatisticCount> universityStatic() {
        return statisticsCandidateRepository.countUniversity();
    }

    @Override
    public List<StatisticCount> educationForCandidateStatic() {
        return statisticsCandidateRepository.countEducation();
    }

    @Override
    public List<StatisticCount> experienceStatic() {
        return statisticsCandidateRepository.countExperience();
    }

    @Override
    public List<StatisticCount> directionsStatic() {
        return statisticsCandidateRepository.countDirections();
    }

    @Override
    public List<StatisticCount> channelStatic() {
        return statisticsCandidateRepository.countChannel();
    }
}

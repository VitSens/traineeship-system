package ru.coda.traineeship.statistics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.coda.traineeship.statistics.model.TestUserModel;
import ru.coda.traineeship.statistics.util.StatisticCount;

import java.util.List;

@Repository("StatisticsRepository")
public interface StatisticsRepository extends JpaRepository<TestUserModel, Long> {

    @Query("SELECT u.age, COUNT(u.age) AS c FROM TestUserModel u GROUP BY u.age")
    List<StatisticCount> countAge();
    @Query("SELECT u.city, COUNT(u.city) AS c FROM TestUserModel u GROUP BY u.city")
    List<StatisticCount> countCity();

    @Query("SELECT u.university, COUNT(u.university) AS c FROM TestUserModel u GROUP BY u.university")
    List<StatisticCount> countUniversity();

    @Query("SELECT u.education, COUNT(u.education) AS c FROM TestUserModel u GROUP BY u.education")
    List<StatisticCount> countEducation();

    @Query("SELECT u.experience, COUNT(u.experience) AS c FROM TestUserModel u GROUP BY u.experience")
    List<StatisticCount> countExperience();

    @Query("SELECT u.directions, COUNT(u.directions) AS c FROM TestUserModel u GROUP BY u.directions")
    List<StatisticCount> countDirections();

    @Query("SELECT u.channel, COUNT(u.channel) AS c FROM TestUserModel u GROUP BY u.channel")
    List<StatisticCount> countChannel();
}

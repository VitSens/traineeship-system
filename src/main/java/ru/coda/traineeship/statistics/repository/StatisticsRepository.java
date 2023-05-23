package ru.coda.traineeship.statistics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.coda.traineeship.statistics.model.TestUserModel;
import ru.coda.traineeship.statistics.util.StatisticCount;

import java.util.List;

@Repository("StatisticsRepository")
public interface StatisticsRepository extends JpaRepository<TestUserModel, Long> {

    @Query(value = "SELECT age AS what, COUNT(age) AS total FROM Users GROUP BY age", nativeQuery = true)
    List<StatisticCount> countAge();
    @Query(value = "SELECT city AS what, COUNT(u.city) AS total FROM Users u GROUP BY city", nativeQuery = true)
    List<StatisticCount> countCity();

    @Query(value = "SELECT university AS what, COUNT(u.university) AS total FROM Users u GROUP BY university", nativeQuery = true)
    List<StatisticCount> countUniversity();

    @Query(value = "SELECT education AS what, COUNT(u.education) AS total FROM Users u GROUP BY education", nativeQuery = true)
    List<StatisticCount> countEducation();

    @Query(value = "SELECT experience AS what, COUNT(u.experience) AS total FROM Users u GROUP BY experience", nativeQuery = true)
    List<StatisticCount> countExperience();

    @Query(value = "SELECT directions AS what, COUNT(u.directions) AS total FROM Users u GROUP BY directions", nativeQuery = true)
    List<StatisticCount> countDirections();

    @Query(value = "SELECT u.channel AS what, COUNT(u.channel) AS total FROM Users u GROUP BY channel", nativeQuery = true)
    List<StatisticCount> countChannel();
}

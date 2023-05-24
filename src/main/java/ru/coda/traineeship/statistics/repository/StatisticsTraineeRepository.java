package ru.coda.traineeship.statistics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.coda.traineeship.statistics.model.TestUserModel;
import ru.coda.traineeship.statistics.util.StatisticCount;

import java.util.List;

@Repository("StatisticsTraineeRepository")
public interface StatisticsTraineeRepository extends JpaRepository<TestUserModel, Long> {
    @Query(value = "SELECT response AS what, COUNT(response) AS total FROM Users GROUP BY response", nativeQuery = true)
    List<StatisticCount> countResponse();
    @Query(value = "SELECT age AS what, COUNT(age) AS total FROM Users GROUP BY age", nativeQuery = true)
    List<StatisticCount> countTask();
    @Query(value = "SELECT education AS what, COUNT(education) AS total FROM Users GROUP BY education", nativeQuery = true)
    List<StatisticCount> countEducation();

    @Query(value = "SELECT government AS what, COUNT(government) AS total FROM Users GROUP BY government", nativeQuery = true)
    List<StatisticCount> countGovernment();
}

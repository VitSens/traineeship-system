package ru.coda.traineeship.statistics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.coda.traineeship.statistics.util.StatisticCount;
import ru.coda.traineeship.vacancy.model.Vacancy;

import java.util.List;

@Repository("StatisticsTraineeRepository")
public interface StatisticsTraineeRepository extends JpaRepository<Vacancy, Long> {
    @Query("SELECT v.company.type AS what, COUNT(v.company.type) AS total FROM Vacancy AS v GROUP BY v.company.type")
    List<StatisticCount> countResponse();

    @Query(value = "SELECT task AS what, COUNT(task) AS total FROM Vacancy GROUP BY task", nativeQuery = true)
    List<StatisticCount> countTask();

    @Query(value = "SELECT education AS what, COUNT(education) AS total FROM Vacancy GROUP BY education", nativeQuery = true)
    List<StatisticCount> countEducation();
}

package ru.coda.traineeship.statistics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.coda.traineeship.recruitment.model.Resume;
import ru.coda.traineeship.statistics.util.StatisticCount;

import java.util.List;

@Repository("StatisticsCandidateRepository")
public interface StatisticsCandidateRepository extends JpaRepository<Resume, Long> {

    @Query(value = "SELECT response AS what, COUNT(response) AS total FROM Resume GROUP BY response", nativeQuery = true)
    List<StatisticCount> countResponse();
    @Query(value = "SELECT age AS what, COUNT(age) AS total FROM Resume GROUP BY age", nativeQuery = true)
    List<StatisticCount> countAge();
    @Query(value = "SELECT location AS what, COUNT(location) AS total FROM Resume GROUP BY location", nativeQuery = true)
    List<StatisticCount> countCity();

    @Query(value = "SELECT university AS what, COUNT(university) AS total FROM Resume GROUP BY university", nativeQuery = true)
    List<StatisticCount> countUniversity();

    @Query(value = "SELECT education AS what, COUNT(education) AS total FROM Resume GROUP BY education", nativeQuery = true)
    List<StatisticCount> countEducation();

    @Query(value = "SELECT experience AS what, COUNT(experience) AS total FROM Resume GROUP BY experience", nativeQuery = true)
    List<StatisticCount> countExperience();

    @Query(value = "SELECT specialization AS what, COUNT(specialization) AS total FROM Resume GROUP BY specialization", nativeQuery = true)
    List<StatisticCount> countDirections();

    @Query(value = "SELECT channel AS what, COUNT(channel) AS total FROM Resume GROUP BY channel", nativeQuery = true)
    List<StatisticCount> countChannel();
}

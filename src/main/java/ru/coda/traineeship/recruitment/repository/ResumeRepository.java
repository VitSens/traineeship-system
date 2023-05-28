package ru.coda.traineeship.recruitment.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.coda.traineeship.recruitment.model.Resume;
import ru.coda.traineeship.vacancy.model.Vacancy;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, Integer>, JpaSpecificationExecutor<Resume> {

  @Query("select r from Resume r where r.userId = :userId")
  List<Resume> findAllByUserId(@Param("userId") String userId);

  @Query("select r from Resume r where r.userId = :userId")
  List<Resume> findByUserId(@Param("userId") Integer userId);

  @Query("select v from Vacancy v "
         + "where v.userId = :userId")
  List<Vacancy> findAllVacancyByUserId(@Param("userId") String userId);

  Resume findByUserIdAndId(String userId, Integer id);

  @Query("select r.location from Resume r where lower(r.location) like lower(concat(:location, '%'))")
  List<String> findAllByLocation(@Param("location") String location);

  @Query("select r.specialization from Resume r where lower(r.specialization) like lower(concat(:specialization, '%'))")
  List<String> findAllBySpecialization(@Param("specialization") String specialization);
}

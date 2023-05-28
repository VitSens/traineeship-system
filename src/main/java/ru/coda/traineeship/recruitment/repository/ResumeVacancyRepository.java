package ru.coda.traineeship.recruitment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.coda.traineeship.recruitment.model.Resume;
import ru.coda.traineeship.recruitment.model.ResumeVacancy;

@Repository
public interface ResumeVacancyRepository extends JpaRepository<ResumeVacancy, Integer> {

  @Query("select r from ResumeVacancy r where r.resume.id = :id")
  ResumeVacancy findByResumeId(@Param("id") Integer id);

}

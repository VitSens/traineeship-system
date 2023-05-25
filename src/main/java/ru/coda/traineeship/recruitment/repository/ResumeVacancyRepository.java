package ru.coda.traineeship.recruitment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.coda.traineeship.recruitment.model.ResumeVacancy;

@Repository
public interface ResumeVacancyRepository extends JpaRepository<ResumeVacancy, Integer> {

  ResumeVacancy findByResume(Integer id);

}

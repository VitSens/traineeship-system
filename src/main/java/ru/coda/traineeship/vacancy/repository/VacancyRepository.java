package ru.coda.traineeship.vacancy.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.coda.traineeship.vacancy.model.Vacancy;
import ru.coda.traineeship.vacancy.model.VacancyStatus;

@Repository
public interface VacancyRepository extends JpaRepository<Vacancy, Integer>, JpaSpecificationExecutor<Vacancy> {

  Vacancy findByIdAndStatus(Integer id, VacancyStatus status);
}

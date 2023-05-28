package ru.coda.traineeship.vacancy.service;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.data.jpa.domain.Specification;
import ru.coda.traineeship.vacancy.model.Vacancy;
import ru.coda.traineeship.vacancy.model.VacancyStatus;
import ru.coda.traineeship.vacancy.model.dto.VacancyRequestDto;

public interface VacancyService {

  List<Vacancy> findAll();

  List<Vacancy> findAll(Specification<Vacancy> filter, HttpServletRequest request);

  Vacancy findById(Integer vacancyId, HttpServletRequest request);

  Vacancy findByIdAndStatus(Integer vacancyId, VacancyStatus status);

  boolean existsById(Integer vacancyId);

  Vacancy save(Vacancy vacancy);

  Vacancy create(VacancyRequestDto vacancy, HttpServletRequest request);

  Vacancy publishVacancy(Integer vacancyId);

  void cancelVacancy(Integer vacancyId);
}

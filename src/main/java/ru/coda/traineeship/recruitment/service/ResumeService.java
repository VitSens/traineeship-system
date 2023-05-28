package ru.coda.traineeship.recruitment.service;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import ru.coda.traineeship.recruitment.model.Resume;
import ru.coda.traineeship.configure.specification.FilterDto;
import ru.coda.traineeship.recruitment.model.ResumeVacancy;
import ru.coda.traineeship.vacancy.model.Vacancy;

public interface ResumeService {

  List<Resume> findAll(String search);

  Resume findById(Integer resumeId);

  List<Resume> findAllById(Integer resumeId);

  List<Resume> findAllByUserId(String userId);

  List<Vacancy> findAllVacancyByUserId(String userId);

  Resume findByUserIdAndResumeId(String userId, Integer resumeId);

  List<String> findAllByLocation(String location);

  List<String> findAllBySpecialization(String specialization);

  Resume createResume(String userId, Resume resume, HttpServletRequest request);

  String submitResume(Integer resumed, Integer vacancyId);
}

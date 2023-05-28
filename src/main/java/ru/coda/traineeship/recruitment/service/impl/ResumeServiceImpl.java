package ru.coda.traineeship.recruitment.service.impl;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import ru.coda.traineeship.configure.specification.SearchOperation;
import ru.coda.traineeship.recruitment.model.ResumeVacancy;
import ru.coda.traineeship.recruitment.model.Resume;
import ru.coda.traineeship.configure.specification.FilterDto;
import ru.coda.traineeship.configure.specification.SearchCriteria;
import ru.coda.traineeship.configure.specification.SpecificationBuilder;
import ru.coda.traineeship.recruitment.model.ResumeVacancyStatus;
import ru.coda.traineeship.recruitment.repository.ResumeRepository;
import ru.coda.traineeship.recruitment.repository.ResumeVacancyRepository;
import ru.coda.traineeship.recruitment.service.ResumeService;
import ru.coda.traineeship.vacancy.model.Vacancy;
import ru.coda.traineeship.vacancy.model.VacancyStatus;
import ru.coda.traineeship.vacancy.service.VacancyService;

@Service
@AllArgsConstructor
public class ResumeServiceImpl implements ResumeService {

  private final ResumeRepository resumeRepository;
  private final ResumeVacancyRepository resumeVacancyRepository;
  private final VacancyService vacancyService;

  private final ModelMapper modelMapper;

  @Override
  public List<Resume> findAll(@RequestParam(value = "search") String search) {
    SpecificationBuilder<Resume> builder = new SpecificationBuilder<>();
    String operationSetExper = String.join("|", SearchOperation.SIMPLE_OPERATION_SET);
    Pattern pattern = Pattern.compile(
        "(\\w+?)(" + operationSetExper + ")(\\p{Punct}?)(\\w+?)(\\p{Punct}?),");
    Matcher matcher = pattern.matcher(search + ",");
    while (matcher.find()) {
      builder.with(
          matcher.group(1),
          matcher.group(2),
          matcher.group(4),
          matcher.group(3),
          matcher.group(5));
    }

    Specification<Resume> spec = builder.build();

    return resumeRepository.findAll(spec);
  }

  @Override
  public Resume findById(Integer resumeId) {
    return resumeRepository.findById(resumeId).orElse(null);
  }

  @Override
  public List<Resume> findAllById(Integer resumeId) {
    return resumeRepository.findByUserId(resumeId);
  }

  @Override
  public List<Resume> findAllByUserId(String userId) {
    return resumeRepository.findAllByUserId(userId);
  }

  @Override
  public List<Vacancy> findAllVacancyByUserId(String userId) {
    return resumeRepository.findAllVacancyByUserId(userId);
  }

  @Override
  public Resume findByUserIdAndResumeId(String userId, Integer resumeId) {
    return resumeRepository.findByUserIdAndId(userId, resumeId);
  }

  @Override
  public List<String> findAllByLocation(String location) {
    return resumeRepository.findAllByLocation(location);
  }

  @Override
  public List<String> findAllBySpecialization(String specialization) {
    return resumeRepository.findAllBySpecialization(specialization);
  }

  @Override
  public Resume createResume(String userId, Resume resume, HttpServletRequest request) {
    resume.setUserId(userId);

    return resumeRepository.save(resume);
  }

  @Override
  @Transactional
  public String submitResume(Integer resumeId, Integer vacancyId) {
    Resume resume = resumeRepository.findById(resumeId).get();
    Vacancy vacancy = vacancyService.findByIdAndStatus(vacancyId, VacancyStatus.PUBLISHED);

    if (vacancy == null) throw new InvalidParameterException();

    ResumeVacancy resumeVacancy = new ResumeVacancy(resume, vacancy, ResumeVacancyStatus.WAITING);

    resumeVacancy.setResume(resume);
    resumeVacancy.setVacancy(vacancy);
    resumeVacancyRepository.save(resumeVacancy);

//    vacancy.getResumes().add(resumeVacancy);
//    vacancyService.save(vacancy);

    return "ok";
  }

}

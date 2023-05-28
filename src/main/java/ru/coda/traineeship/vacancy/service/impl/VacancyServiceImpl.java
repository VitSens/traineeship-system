package ru.coda.traineeship.vacancy.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.keycloak.representations.AccessToken;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.coda.traineeship.company.model.Company;
import ru.coda.traineeship.company.service.CompanyService;
import ru.coda.traineeship.utils.KeycloakUtils;
import ru.coda.traineeship.vacancy.model.Vacancy;
import ru.coda.traineeship.vacancy.model.VacancyStatus;
import ru.coda.traineeship.vacancy.model.dto.VacancyRequestDto;
import ru.coda.traineeship.vacancy.repository.VacancyRepository;
import ru.coda.traineeship.vacancy.service.VacancyService;

@Service
@AllArgsConstructor
public class VacancyServiceImpl implements VacancyService {

  private final ModelMapper modelMapper;

  private final CompanyService companyService;
  private final VacancyRepository vacancyRepository;


  @Override
  public List<Vacancy> findAll() {
    return vacancyRepository.findAll();
  }

  @Override
  public List<Vacancy> findAll(Specification<Vacancy> filter, HttpServletRequest request) {
    AccessToken token = KeycloakUtils.getKeycloakUser(request);

    List<Vacancy> vacancyList = new ArrayList<>(vacancyRepository.findAll(filter));

    Set<String> roles = token.getRealmAccess().getRoles();

    if (roles.contains("Intern")) {
      return vacancyList.stream()
                        .filter(v -> v.getStatus().equals(VacancyStatus.PUBLISHED))
                        .collect(Collectors.toList());
    } else if (roles.contains("Curator")) {
      return vacancyList.stream()
                        .filter(v -> v.getUserId().equals(token.getSubject()))
                        .collect(Collectors.toList());
    }

    return vacancyList;
  }

  @Override
  public Vacancy findById(Integer vacancyId, HttpServletRequest request) {
    AccessToken token = KeycloakUtils.getKeycloakUser(request);

    Set<String> roles = token.getRealmAccess().getRoles();
    Vacancy vacancy = vacancyRepository.findById(vacancyId).get();

    if (roles.contains("Intern") && vacancy.getStatus().equals(VacancyStatus.PUBLISHED)) {
      return vacancy;
    }

    return vacancy;
  }

  @Override
  public Vacancy findByIdAndStatus(Integer vacancyId, VacancyStatus status) {
    return vacancyRepository.findByIdAndStatus(vacancyId, status);
  }

  @Override
  public boolean existsById(Integer vacancyId) {
    return vacancyRepository.existsById(vacancyId);
  }

  @Override
  public Vacancy save(Vacancy vacancy) {
    return vacancyRepository.save(vacancy);
  }

  @Override
  public Vacancy create(VacancyRequestDto vacancyRequestDto, HttpServletRequest request) {
    Company company = companyService.findById(vacancyRequestDto.getCompanyId());
    Vacancy vacancy = modelMapper.map(vacancyRequestDto, Vacancy.class);

    vacancy.setCompany(company);
    vacancy.setStatus(VacancyStatus.CREATED);
    vacancy.setUserId(KeycloakUtils.getKeycloakUser(request).getSubject());

    return vacancyRepository.save(vacancy);
  }

  @Override
  public Vacancy publishVacancy(Integer vacancyId) {
    Vacancy vacancy = vacancyRepository.getReferenceById(vacancyId);

    vacancy.setPublishedAt(LocalDateTime.now());
    vacancy.setStatus(VacancyStatus.PUBLISHED);

    return vacancyRepository.save(vacancy);
  }

  @Override
  public void cancelVacancy(Integer vacancyId) {
    Vacancy vacancy = vacancyRepository.getReferenceById(vacancyId);

    if (!vacancy.getStatus().equals(VacancyStatus.CREATED)) throw new RuntimeException();

    vacancyRepository.delete(vacancy);
  }

}

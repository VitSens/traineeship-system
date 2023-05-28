package ru.coda.traineeship.company.service.impl;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.coda.traineeship.company.model.Company;
import ru.coda.traineeship.company.repository.CompanyRepository;
import ru.coda.traineeship.company.service.CompanyService;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

  private final CompanyRepository companyRepository;

  @Override
  public List<Company> findAll() {
    return companyRepository.findAll();
  }

  @Override
  public Company findById(Integer id) {
    return companyRepository.findById(id).get();
  }

  @Override
  public Company create(Company company) {
    return companyRepository.save(company);
  }

  @Override
  public Company update(
      Integer id,
      Company company
  ) {
    return null;
  }

  @Override
  public void delete(Integer id) {

  }
}

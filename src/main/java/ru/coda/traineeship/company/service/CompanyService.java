package ru.coda.traineeship.company.service;

import java.util.List;
import ru.coda.traineeship.company.model.Company;

public interface CompanyService {

  List<Company> findAll();

  Company findById(Integer id);

  Company create(Company company);

  Company update(Integer id, Company company);

  void delete(Integer id);

}

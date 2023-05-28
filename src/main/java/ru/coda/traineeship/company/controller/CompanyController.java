package ru.coda.traineeship.company.controller;

import java.util.List;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.coda.traineeship.company.model.Company;
import ru.coda.traineeship.company.model.dto.CompanyRequestDto;
import ru.coda.traineeship.company.service.CompanyService;

@RestController
@RequestMapping("/v1/company")
@AllArgsConstructor
public class CompanyController {

  private final ModelMapper modelMapper;
  private final CompanyService companyService;

  @GetMapping
  public List<Company> getAllCompany() {
    return companyService.findAll();
  }

  @GetMapping("/{id}")
  public Company getCompanyById(@PathVariable Integer id) {
    return companyService.findById(id);
  }

  @PostMapping
  public Company createCompany(@RequestBody CompanyRequestDto company) {
    return companyService.create(modelMapper.map(company, Company.class));
  }

}

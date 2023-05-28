package ru.coda.traineeship.vacancy.controller;

import java.util.List;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.keycloak.authorization.client.util.Http;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.coda.traineeship.configure.specification.FilterDto;
import ru.coda.traineeship.configure.specification.SearchCriteria;
import ru.coda.traineeship.configure.specification.SearchOperation;
import ru.coda.traineeship.configure.specification.SpecificationBuilder;
import ru.coda.traineeship.quiz.model.support.Response;
import ru.coda.traineeship.vacancy.model.Vacancy;
import ru.coda.traineeship.vacancy.model.VacancyStatus;
import ru.coda.traineeship.vacancy.model.dto.VacancyRequestDto;
import ru.coda.traineeship.vacancy.model.dto.VacancyResponseDto;
import ru.coda.traineeship.vacancy.model.dto.VacancyShortResponseDto;
import ru.coda.traineeship.vacancy.service.VacancyService;

@RestController
@RequestMapping("/v1/vacancy")
@AllArgsConstructor
public class VacancyController {

  private final ModelMapper modelMapper;

  private final VacancyService vacancyService;

  @GetMapping
  public List<VacancyShortResponseDto> getAllVacancy(
      @RequestParam(value = "search", required = false) String search,
      HttpServletRequest httpServlet
  ) {
    SpecificationBuilder<Vacancy> builder = new SpecificationBuilder<Vacancy>();
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

    Specification<Vacancy> spec = builder.build();

    return vacancyService.findAll(spec, httpServlet)
                         .stream()
                         .map(v -> modelMapper.map(v, VacancyShortResponseDto.class))
                         .collect(Collectors.toList());
  }

  @GetMapping("/{vacancyId}")
  public VacancyResponseDto getVacancyById(@PathVariable Integer vacancyId, HttpServletRequest request) {
    Vacancy vacancy = vacancyService.findById(vacancyId, request);
    return modelMapper.map(vacancy, VacancyResponseDto.class);
  }

  @PostMapping
  public Vacancy createVacancy(
      @RequestBody VacancyRequestDto vacancy,
      HttpServletRequest request
  ) {
    return vacancyService.create(vacancy, request);
  }

  @PutMapping("/{vacancyId}/publish")
  public Vacancy publishVacancy(@PathVariable Integer vacancyId) {
    return vacancyService.publishVacancy(vacancyId);
  }

  @DeleteMapping("/{vacancyId}/cancel")
  public ResponseEntity<HttpStatus> cancelVacancy(@PathVariable Integer vacancyId) {
    try {
      vacancyService.cancelVacancy(vacancyId);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}

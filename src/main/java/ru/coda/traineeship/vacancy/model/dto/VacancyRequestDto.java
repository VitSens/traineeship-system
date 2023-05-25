package ru.coda.traineeship.vacancy.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VacancyRequestDto {

  private String title;
  private Integer salary;
  private String description;
  private String shortDescription;
  private Integer companyId;

}

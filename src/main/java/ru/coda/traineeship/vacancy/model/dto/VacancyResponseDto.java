package ru.coda.traineeship.vacancy.model.dto;

import lombok.Data;

@Data
public class VacancyResponseDto {

  private Integer id;
  private String title;
  private String salary;
  private String companyName;
  private String companyLink;
  private String description;

}

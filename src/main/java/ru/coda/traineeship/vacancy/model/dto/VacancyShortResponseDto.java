package ru.coda.traineeship.vacancy.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import ru.coda.traineeship.company.model.Company;
import ru.coda.traineeship.vacancy.model.VacancyStatus;

@Data
public class VacancyShortResponseDto {

  private Integer id;
  private String title;
  private Integer salary;
  @JsonProperty("description") private String shortDescription;
  private String companyName;
  private String companyLink;
  private VacancyStatus status;

}

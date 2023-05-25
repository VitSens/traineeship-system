package ru.coda.traineeship.company.model.dto;

import lombok.Data;

@Data
public class CompanyRequestDto {

  private String image;
  private String name;
  private String description;
  private String link;
  private String address;

}

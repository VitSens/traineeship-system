package ru.coda.traineeship.recruitment.model.dto;

import lombok.Data;

@Data
public class ResumeResponseDto {

  private Integer id;
  private String specialization;
  private String fullName;
  private Integer experience;
  private Integer course;
  private Integer citizenship;
  private String university;
  private String location;
//  private Set<VacancyShortResponseDto> vacancySet;

}

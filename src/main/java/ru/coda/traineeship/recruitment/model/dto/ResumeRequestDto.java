package ru.coda.traineeship.recruitment.model.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ResumeRequestDto {

  private String firstName;
  private String surName;
  private String middleName;
  private String location;
  private Integer experience;
  private Integer course;
  private Integer citizenship;
  private String specialization;
  private String university;

}

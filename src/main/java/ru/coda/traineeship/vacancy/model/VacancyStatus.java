package ru.coda.traineeship.vacancy.model;

public enum VacancyStatus {
  CREATED, PUBLISHED;

  public String value(VacancyStatus status) {
    return status.name();
  }
}

package ru.coda.traineeship.recruitment.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResumeVacancyId implements Serializable {

  @Column(name = "resume_id")
  private Integer resumeId;

  @Column(name = "vacancy_id")
  private Integer vacancyId;

}

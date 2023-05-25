package ru.coda.traineeship.recruitment.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.coda.traineeship.vacancy.model.Vacancy;

@Entity
@Table(name = "resume_vacancy")
@Data
@NoArgsConstructor
public class ResumeVacancy implements Serializable {

  @EmbeddedId
  private ResumeVacancyId id;

  @ManyToOne
  @MapsId("vacancyId")
  @JoinColumn(name = "vacancy_id")
  private Vacancy vacancy;

  @ManyToOne
  @MapsId("resumeId")
  @JoinColumn(name = "resume_id")
  private Resume resume;

  @Column(name = "status")
  private ResumeVacancyStatus status;


  public ResumeVacancy(Resume resume, Vacancy vacancy, ResumeVacancyStatus status) {
    this.id = new ResumeVacancyId(resume.getId(), vacancy.getId());
    this.resume = resume;
    this.vacancy = vacancy;
    this.status = status;
  }

}

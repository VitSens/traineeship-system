package ru.coda.traineeship.vacancy.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.lang.Nullable;
import ru.coda.traineeship.company.model.Company;
import ru.coda.traineeship.recruitment.model.ResumeVacancy;

@Entity
@Table
@Data
@EqualsAndHashCode(exclude = {"resumes", "company"})
@ToString(exclude = {"resumes", "company"})
@NoArgsConstructor
public class Vacancy {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @CreatedDate
  private LocalDateTime createdAt;

  @UpdateTimestamp
  private LocalDateTime updateAt;

  @Column
  @Nullable
  private LocalDateTime publishedAt;

  @Column
  @NotNull
  private String title;

  @Column
  @NotNull
  private String description;

  @Column
  @NotNull
  private String shortDescription;

  @Column
  @Nullable
  private String education;

  @Column
  @Nullable
  private Integer task;

  @Column
  @NotNull
  @Min(0)
  private Integer salary;

  @NotNull
  @Column
  @Enumerated(EnumType.STRING)
  private VacancyStatus status;

  @Column
  @NotNull
  private String userId;

  @OneToMany(mappedBy = "resume")
  @JsonIgnore
  private Set<ResumeVacancy> resumes = new HashSet<>();

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name="company_id", nullable=true)
  @JsonIgnore
  private Company company;

}

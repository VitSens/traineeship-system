package ru.coda.traineeship.recruitment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.lang.Nullable;
import ru.coda.traineeship.rating.model.RatingCompany;
import ru.coda.traineeship.rating.model.RatingTrainee;
import ru.coda.traineeship.vacancy.model.Vacancy;

@Entity
@Table(name = "resume")
@Data
@EqualsAndHashCode(exclude = "vacancySet")
@ToString(exclude = "vacancySet")
@NoArgsConstructor
public class Resume {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column
  private Integer id;

  @Column
  @NotNull
  private String userId;

  @Column
  @NotNull
  private String firstName;

  @Column
  @NotNull
  private String surName;

  @Column
  @NotNull
  private String middleName;

  @Column
  @Nullable
  private Integer age;

  @Column
  @NotNull
  private String location;

  @Column
  @NotNull
  @Min(0)
  private Integer experience;

  @Column
  @Min(0)
  @Max(4)
  private Integer course;

  @Column
  @NotNull
  @Max(1)
  @Min(0)
  private Integer citizenship;

  @Column
  @NotNull
  private String specialization;

  @Column
  @NotNull
  private String university;

  @Column
  @Nullable
  private String education;

  @Column
  @Nullable
  private String channel;

  @OneToMany(mappedBy = "vacancy")
  @JsonIgnore
  private Set<ResumeVacancy> vacancySet = new HashSet<>();

  @Nullable
  @OneToMany(mappedBy = "resume")
  @JsonIgnore
  private Set<RatingTrainee> ratingTraineeSet = new HashSet<>();

  @Column
  @Nullable
  private boolean response;
}

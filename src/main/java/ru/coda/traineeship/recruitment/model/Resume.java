package ru.coda.traineeship.recruitment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Set;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Singular;
import org.springframework.context.annotation.Lazy;
import org.springframework.lang.Nullable;
import ru.coda.traineeship.vacancy.model.Vacancy;

@Entity
@Table(name = "resume")
@Data
@EqualsAndHashCode
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

  @ManyToMany
  @Lazy
  private Set<Vacancy> vacancySet;

}

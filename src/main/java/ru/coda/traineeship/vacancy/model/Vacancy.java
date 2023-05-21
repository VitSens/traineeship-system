package ru.coda.traineeship.vacancy.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.lang.Nullable;
import ru.coda.traineeship.recruitment.model.Resume;

@Entity
@Table
@Data
@EqualsAndHashCode(exclude = "resumes")
@NoArgsConstructor
public class Vacancy {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @NotNull
  private String title;

  @Nullable
  private String description;

  @NotNull
  private String company;

  @ManyToMany
  @Lazy
  @JsonIgnore
  private Set<Resume> resumes = new HashSet<>();

}

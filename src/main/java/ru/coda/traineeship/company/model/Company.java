package ru.coda.traineeship.company.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;
import ru.coda.traineeship.vacancy.model.Vacancy;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Company {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column
  private Integer id;

  @Column
  @Nullable
  private String image;

  @Column
  @NotNull
  private String name;

  @Column
  @NotNull
  private String description;

  @Column
  @NotNull
  private String link;

  @Column
  @NotNull
  private String address;

  @OneToMany(mappedBy = "company")
  @JsonIgnore
  private Set<Vacancy> vacancySet;

}

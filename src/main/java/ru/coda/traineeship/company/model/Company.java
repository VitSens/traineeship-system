package ru.coda.traineeship.company.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;
import ru.coda.traineeship.rating.model.RatingCompany;
import ru.coda.traineeship.vacancy.model.Vacancy;
import ru.coda.traineeship.vacancy.model.VacancyStatus;

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

  @Nullable
  @Column
  @Enumerated(EnumType.STRING)
  private CompanyType type;

  @OneToMany(mappedBy = "company")
  @JsonIgnore
  private Set<Vacancy> vacancySet;

  @Nullable
  @OneToMany(mappedBy = "company")
  @JsonIgnore
  private Set<RatingCompany> ratingCompanySet;

}

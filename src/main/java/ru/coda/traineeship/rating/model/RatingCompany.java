package ru.coda.traineeship.rating.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.coda.traineeship.company.model.Company;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "rating_company")
public class RatingCompany extends BaseRating{

    @ManyToOne
    @JoinColumn(name = "id")
    @JsonIgnore
    private Company company;
}

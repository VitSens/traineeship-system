package ru.coda.traineeship.rating.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.coda.traineeship.recruitment.model.Resume;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "rating_trainee")
public class RatingTrainee extends BaseRating{
    @ManyToOne
    @JoinColumn(name = "id")
    @JsonIgnore
    private Resume resume;
}

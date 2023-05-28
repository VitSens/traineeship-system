package ru.coda.traineeship.rating.model;

import ru.coda.traineeship.quiz.model.BaseModel;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@MappedSuperclass
public abstract class BaseRating extends BaseModel {

    @Column(name = "grade")
    @NotNull
    @Max(5)
    @Min(0)
    private Integer grade;

    @Column(name ="review")
    @NotNull
    private String review;
}

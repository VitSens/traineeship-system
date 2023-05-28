package ru.coda.traineeship.rating.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import ru.coda.traineeship.recruitment.model.ResumeVacancy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "mentor")
@Data
public class Mentor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer id;

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
    private Integer age;

    @OneToMany(mappedBy = "mentor")
    @JsonIgnore
    private Set<RatingMentor> mentorSet = new HashSet<>();
}

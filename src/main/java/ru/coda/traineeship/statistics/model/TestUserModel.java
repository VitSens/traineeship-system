package ru.coda.traineeship.statistics.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class TestUserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @NotNull
    private Integer age;

    @Column
    @NotNull
    private String city;

    @Column
    @NotNull
    private String university;

    @Column
    @NotNull
    private String education;

    @Column
    @NotNull
    private Integer experience;

    @Column
    @NotNull
    private boolean response;

    @Column
    @NotNull
    private String directions;

    @Column
    @NotNull
    private String channel;


}

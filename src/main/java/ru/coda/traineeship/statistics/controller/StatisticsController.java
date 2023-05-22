package ru.coda.traineeship.statistics.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(StatisticsController.ROOT_MAPPING)
public class StatisticsController {
    public static final String ROOT_MAPPING = "/api/statistics/candidate";
}

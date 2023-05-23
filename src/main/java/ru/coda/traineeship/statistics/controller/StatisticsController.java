package ru.coda.traineeship.statistics.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.coda.traineeship.statistics.service.abstracts.StatisticsService;
import ru.coda.traineeship.statistics.util.StatisticCount;

import java.util.List;

@RestController
@RequestMapping(StatisticsController.ROOT_MAPPING)
public class StatisticsController {
    public static final String ROOT_MAPPING = "/api/statistics/candidate";

    private final StatisticsService statisticsService;

    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @RequestMapping(value = "/{what}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<StatisticCount>> getStatic(@PathVariable String what) {
        List<StatisticCount> result = statisticsService.getStatic(what);
        return  ResponseEntity.ok(result);
    }

}

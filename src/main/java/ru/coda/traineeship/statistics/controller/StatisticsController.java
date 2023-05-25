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
    public static final String ROOT_MAPPING = "/api/statistics";

    private final StatisticsService statisticsService;

    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @RequestMapping(value = "/candidate/{what}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<StatisticCount>> getStaticForCandidate(@PathVariable String what) {
        List<StatisticCount> result = statisticsService.getStaticForCandidate(what);
        result.add(new StatisticCount() {
            @Override
            public String getWhat() {
                return "count";
            }

            @Override
            public Integer getTotal() {
                return what.equals("response")? statisticsService.responseCount() : result.size() - 1;
            }
        });
        return  ResponseEntity.ok(result);
    }
    @RequestMapping(value = "/trainee/{what}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<StatisticCount>> getStaticForTrainee(@PathVariable String what) {
        List<StatisticCount> result = statisticsService.getStaticForTrainee(what);
        result.add(new StatisticCount() {
            @Override
            public String getWhat() {
                return "count";
            }

            @Override
            public Integer getTotal() {
                return result.size() - 1;
            }
        });
        return  ResponseEntity.ok(result);
    }

}

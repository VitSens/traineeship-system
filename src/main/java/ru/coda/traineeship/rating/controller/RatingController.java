package ru.coda.traineeship.rating.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.coda.traineeship.company.model.Company;
import ru.coda.traineeship.rating.model.Mentor;
import ru.coda.traineeship.rating.model.RatingCompany;
import ru.coda.traineeship.rating.model.RatingMentor;
import ru.coda.traineeship.rating.model.RatingTrainee;
import ru.coda.traineeship.rating.service.abstracts.RatingService;
import ru.coda.traineeship.recruitment.model.Resume;

import java.util.List;

@RestController
@RequestMapping(RatingController.ROOT_MAPPING)
public class RatingController {
    public static final String ROOT_MAPPING = "/api/rating";
    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }


    @RequestMapping(value = "/company/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<RatingCompany>> getRatingCompany(@PathVariable Integer id) {
        List<RatingCompany> result = ratingService.ratingCompany(id);
        return  ResponseEntity.ok(result);
    }
    @RequestMapping(value = "/trainee/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<RatingTrainee>> getRatingTrainee(@PathVariable Integer id) {
        List<RatingTrainee> result = ratingService.ratingTrainee(id);
        return  ResponseEntity.ok(result);
    }
    @RequestMapping(value = "/mentor/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<RatingMentor>> getRatingMentor(@PathVariable Integer id) {
        List<RatingMentor> result = ratingService.ratingMentor(id);
        return  ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/company", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Company>> getRatingCompany() {
        List<Company> result = ratingService.ratingCompany();
        return  ResponseEntity.ok(result);
    }
    @RequestMapping(value = "/trainee", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Resume>> getRatingTrainee() {
        List<Resume> result = ratingService.ratingTrainee();
        return  ResponseEntity.ok(result);
    }
    @RequestMapping(value = "/mentor", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Mentor>> getRatingMentor() {
        List<Mentor> result = ratingService.ratingMentor();
        return  ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/mentor", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<HttpStatus> createMentorReview(@RequestBody RatingMentor review) {
        ratingService.saveMentorReview(review);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @RequestMapping(value = "/trainee", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<HttpStatus> createTraineeReview(@RequestBody RatingTrainee review) {
        ratingService.saveTraineeReview(review);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @RequestMapping(value = "/company", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<HttpStatus> createCompanyReview(@RequestBody RatingCompany review) {
        ratingService.saveCompanyReview(review);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}

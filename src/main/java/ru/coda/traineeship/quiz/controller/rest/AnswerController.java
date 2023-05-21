package ru.coda.traineeship.quiz.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.coda.traineeship.quiz.controller.util.RestVerifier;
import ru.coda.traineeship.quiz.model.Answer;
import ru.coda.traineeship.quiz.model.Question;
import ru.coda.traineeship.quiz.service.abstracts.AnswerService;
import ru.coda.traineeship.quiz.service.abstracts.QuestionService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(AnswerController.ROOT_MAPPING)
public class AnswerController {
    public static final String ROOT_MAPPING = "/api/answers";
    @Autowired
    AnswerService answerService;

    @Autowired
    QuestionService questionService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Answer save(@Valid Answer answer, BindingResult result, @RequestParam long question_id) {

        RestVerifier.verifyModelResult(result);

        Question question = questionService.find(question_id);
        return questionService.addAnswerToQuestion(answer, question);
    }
    @RequestMapping(value = "/updateAll", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void updateAll(@RequestBody List<Answer> answers) {
        for (int i = 0; i < answers.size(); i++) {
            Answer answer = answers.get(i);
            answer.setOrder(i + 1);

            answerService.update(answer);
        }
    }

    @RequestMapping(value = "/{answer_id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Answer find(@PathVariable Long answer_id) {

        return answerService.find(answer_id);
    }

    @RequestMapping(value = "/{answer_id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public Answer update(@PathVariable Long answer_id, @Valid Answer answer, BindingResult result) {

        RestVerifier.verifyModelResult(result);

        answer.setId(answer_id);
        return answerService.update(answer);
    }

    @RequestMapping(value = "/{answer_id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long answer_id) {
        Answer answer = answerService.find(answer_id);
        answerService.delete(answer);
    }
}

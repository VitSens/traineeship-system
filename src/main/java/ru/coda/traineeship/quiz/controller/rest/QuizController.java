package ru.coda.traineeship.quiz.controller.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.coda.traineeship.quiz.controller.util.RestVerifier;
import ru.coda.traineeship.quiz.model.Question;
import ru.coda.traineeship.quiz.model.Quiz;
import ru.coda.traineeship.quiz.model.support.Response;
import ru.coda.traineeship.quiz.model.support.Result;
import ru.coda.traineeship.quiz.service.abstracts.QuestionService;
import ru.coda.traineeship.quiz.service.abstracts.QuizService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(QuizController.ROOT_MAPPING)
public class QuizController {

	public static final String ROOT_MAPPING = "/api/quizzes";
	
	private static final Logger logger = LoggerFactory.getLogger(QuizController.class);

	@Autowired
	private QuizService quizService;

	@Autowired
	private QuestionService questionService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Page<Quiz> findAll(Pageable pageable,
							  @RequestParam(required = false, defaultValue = "false") Boolean published) {
		
		if (published) {
			return quizService.findAllPublished(pageable);
		} else {
			return quizService.findAll(pageable);
		}
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Page<Quiz> searchAll(Pageable pageable, @RequestParam(required = true) String filter,
			@RequestParam(required = false, defaultValue = "false") Boolean onlyValid) {

		return quizService.search(filter, pageable);
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Quiz save(@Valid Quiz quiz, BindingResult result) {

		logger.debug("The Quiz " + quiz.getName() + " is going to be created");
		
		RestVerifier.verifyModelResult(result);

		return quizService.save(quiz);
	}

	@RequestMapping(value = "/{quiz_id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Quiz find(@PathVariable Long quiz_id) {

		return quizService.find(quiz_id);
	}

	@RequestMapping(value = "/{quiz_id}", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public Quiz update(@PathVariable Long quiz_id, @Valid Quiz quiz, BindingResult result) {

		RestVerifier.verifyModelResult(result);

		quiz.setId(quiz_id);
		return quizService.update(quiz);
	}

	@RequestMapping(value = "/{quiz_id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable Long quiz_id) {
		Quiz quiz = quizService.find(quiz_id);
		quizService.delete(quiz);
	}

	@RequestMapping(value = "/{quiz_id}/questions", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<Question> findQuestions(@PathVariable Long quiz_id,
										@RequestParam(required = false, defaultValue = "false") Boolean onlyValid) {

		Quiz quiz = quizService.find(quiz_id);

		if (onlyValid) {
			return questionService.findValidQuestionsByQuiz(quiz);
		} else {
			return questionService.findQuestionsByQuiz(quiz);
		}

	}

	@RequestMapping(value = "/{quiz_id}/publish", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public void publishQuiz(@PathVariable long quiz_id) {
		Quiz quiz = quizService.find(quiz_id);
		quizService.publishQuiz(quiz);
	}

	@RequestMapping(value = "/{quiz_id}/submitAnswers", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public Result playQuiz(@PathVariable long quiz_id, @RequestBody List<Response> answersBundle) {
		Quiz quiz = quizService.find(quiz_id);
		return quizService.checkAnswers(quiz, answersBundle);
	}

}

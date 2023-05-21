package ru.coda.traineeship.quiz.controller.rest;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.coda.traineeship.quiz.controller.util.RestVerifier;
import ru.coda.traineeship.quiz.model.Answer;
import ru.coda.traineeship.quiz.model.Question;
import ru.coda.traineeship.quiz.model.Quiz;
import ru.coda.traineeship.quiz.service.abstracts.AnswerService;
import ru.coda.traineeship.quiz.service.abstracts.QuestionService;
import ru.coda.traineeship.quiz.service.abstracts.QuizService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(QuestionController.ROOT_MAPPING)
public class QuestionController {

	public static final String ROOT_MAPPING = "/api/questions";

	private final QuestionService questionService;

	private final QuizService quizService;

	private final AnswerService answerService;

	public QuestionController(QuestionService questionService, QuizService quizService, AnswerService answerService) {
		this.questionService = questionService;
		this.quizService = quizService;
		this.answerService = answerService;
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Question save(@Valid Question question, BindingResult result, @RequestParam Long quiz_id) {

		RestVerifier.verifyModelResult(result);

		Quiz quiz = quizService.find(quiz_id);
		question.setQuiz(quiz);

		return questionService.save(question);
	}

	@RequestMapping(value = "/updateAll", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public void updateAll(@RequestBody List<Question> questions) {
		for (int i = 0; i < questions.size(); i++) {
			Question question = questions.get(i);
			question.setOrder(i + 1);

			questionService.update(question);
		}
	}

	@RequestMapping(value = "/{question_id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Question find(@PathVariable Long question_id) {

		return questionService.find(question_id);
	}

	@RequestMapping(value = "/{question_id}", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public Question update(@PathVariable Long question_id, @Valid Question question, BindingResult result) {

		RestVerifier.verifyModelResult(result);

		question.setId(question_id);
		return questionService.update(question);

	}

	@RequestMapping(value = "/{question_id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable Long question_id) {
		Question question = questionService.find(question_id);
		questionService.delete(question);
	}

	@RequestMapping(value = "/{question_id}/answers", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<Answer> findAnswers(@PathVariable Long question_id) {
		Question question = questionService.find(question_id);
		return answerService.findAnswersByQuestion(question);
	}

	@RequestMapping(value = "/{question_id}/correctAnswer", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Answer getCorrectAnswer(@PathVariable Long question_id) {
		Question question = questionService.find(question_id);
		return questionService.getCorrectAnswer(question);
	}

	@RequestMapping(value = "/{question_id}/correctAnswer", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public void setCorrectAnswer(@PathVariable Long question_id, @RequestParam Long answer_id) {

		Question question = questionService.find(question_id);
		Answer answer = answerService.find(answer_id);
		questionService.setCorrectAnswer(question, answer);
	}

}

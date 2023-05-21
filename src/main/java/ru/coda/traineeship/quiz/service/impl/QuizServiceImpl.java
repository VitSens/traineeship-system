package ru.coda.traineeship.quiz.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.coda.traineeship.quiz.exceptions.ActionRefusedException;
import ru.coda.traineeship.quiz.exceptions.InvalidParametersException;
import ru.coda.traineeship.quiz.exceptions.ResourceUnavailableException;
import ru.coda.traineeship.quiz.exceptions.UnauthorizedActionException;
import ru.coda.traineeship.quiz.model.Question;
import ru.coda.traineeship.quiz.model.Quiz;
import ru.coda.traineeship.quiz.model.support.Response;
import ru.coda.traineeship.quiz.model.support.Result;
import ru.coda.traineeship.quiz.repository.QuizRepository;
import ru.coda.traineeship.quiz.service.abstracts.QuestionService;
import ru.coda.traineeship.quiz.service.abstracts.QuizService;

import java.util.List;

@Service("QuizService")
@Transactional
public class QuizServiceImpl implements QuizService {

	private static final Logger logger = LoggerFactory.getLogger(QuizServiceImpl.class);
	private final QuizRepository quizRepository;

	private final QuestionService questionService;

	@Autowired
	public QuizServiceImpl(QuizRepository quizRepository, QuestionService questionService) {
		this.quizRepository = quizRepository;
		this.questionService = questionService;
	}

	@Override
	public Quiz save(Quiz quiz) {
		return quizRepository.save(quiz);
	}

	@Override
	public Page<Quiz> findAll(Pageable pageable) {
		return quizRepository.findAll(pageable);
	}

	@Override
	public Page<Quiz> findAllPublished(Pageable pageable) {
		return quizRepository.findByIsPublishedTrue(pageable);
	}

	@Override
	public Quiz find(Long id) throws ResourceUnavailableException {
		if (quizRepository.findById(id).isPresent()) {
			return quizRepository.findById(id).get();
		} else {
			logger.error("Quiz " + id + " not found");
			throw new ResourceUnavailableException("Quiz " + id + " not found");
		}
	}

	@Override
	public Quiz update(Quiz newQuiz) throws UnauthorizedActionException, ResourceUnavailableException {
		Quiz currentQuiz = find(newQuiz.getId());

		mergeQuizzes(currentQuiz, newQuiz);
		return quizRepository.save(currentQuiz);
	}

	@Override
	public void delete(Quiz quiz) throws ResourceUnavailableException, UnauthorizedActionException {
		quizRepository.delete(quiz);
	}

	private void mergeQuizzes(Quiz currentQuiz, Quiz newQuiz) {
		currentQuiz.setName(newQuiz.getName());
		currentQuiz.setDescription(newQuiz.getDescription());
	}

	@Override
	public Page<Quiz> search(String query, Pageable pageable) {
		return quizRepository.searchByName(query, pageable);
	}

	@Override
	public Result checkAnswers(Quiz quiz, List<Response> answersBundle) {
		Result results = new Result();

		for (Question question : quiz.getQuestions()) {
			boolean isFound = false;

			if (!question.getIsValid()) {
				continue;
			}

			for (Response bundle : answersBundle) {
				if (bundle.getQuestion().equals(question.getId())) {
					isFound = true;
					results.addAnswer(questionService.checkIsCorrectAnswer(question, bundle.getSelectedAnswer()));
					break;
				}
			}

			if (!isFound) {
				throw new InvalidParametersException("No answer found for question: " + question.getText());
			}
		}

		return results;
	}

	@Override
	public void publishQuiz(Quiz quiz) {
		int count = questionService.countValidQuestionsInQuiz(quiz);

		if (count > 0) {
			quiz.setIsPublished(true);
			quizRepository.save(quiz);
		} else {
			throw new ActionRefusedException("The quiz doesn't have any valid questions");
		}
	}

}

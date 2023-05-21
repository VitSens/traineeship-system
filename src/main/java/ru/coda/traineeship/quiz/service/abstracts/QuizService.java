package ru.coda.traineeship.quiz.service.abstracts;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.coda.traineeship.quiz.exceptions.ResourceUnavailableException;
import ru.coda.traineeship.quiz.exceptions.UnauthorizedActionException;
import ru.coda.traineeship.quiz.model.Quiz;
import ru.coda.traineeship.quiz.model.support.Response;
import ru.coda.traineeship.quiz.model.support.Result;

import java.util.List;

public interface QuizService {
	Quiz save(Quiz quiz);

	Page<Quiz> findAll(Pageable pageable);

	Page<Quiz> findAllPublished(Pageable pageable);

	Quiz find(Long id) throws ResourceUnavailableException;

	Quiz update(Quiz quiz) throws ResourceUnavailableException, UnauthorizedActionException;

	void delete(Quiz quiz) throws ResourceUnavailableException, UnauthorizedActionException;

	Page<Quiz> search(String query, Pageable pageable);

	Result checkAnswers(Quiz quiz, List<Response> answersBundle);

	void publishQuiz(Quiz quiz);
}

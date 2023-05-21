package ru.coda.traineeship.quiz.service.abstracts;

import ru.coda.traineeship.quiz.exceptions.ResourceUnavailableException;
import ru.coda.traineeship.quiz.exceptions.UnauthorizedActionException;
import ru.coda.traineeship.quiz.model.Answer;
import ru.coda.traineeship.quiz.model.Question;
import ru.coda.traineeship.quiz.model.Quiz;

import java.util.List;

public interface QuestionService {
    Question save(Question question) throws UnauthorizedActionException;

    Question find(Long id) throws ResourceUnavailableException;

    List<Question> findQuestionsByQuiz(Quiz quiz);

    List<Question> findValidQuestionsByQuiz(Quiz quiz);

    Question update(Question question) throws ResourceUnavailableException, UnauthorizedActionException;

    void delete(Question question) throws ResourceUnavailableException, UnauthorizedActionException;

    Boolean checkIsCorrectAnswer(Question question, Long answer_id);

    void setCorrectAnswer(Question question, Answer answer);

    Answer getCorrectAnswer(Question question);

    Answer addAnswerToQuestion(Answer answer, Question question);

    int countQuestionsInQuiz(Quiz quiz);

    int countValidQuestionsInQuiz(Quiz quiz);
}

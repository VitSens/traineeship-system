package ru.coda.traineeship.quiz.service.abstracts;

import ru.coda.traineeship.quiz.exceptions.ResourceUnavailableException;
import ru.coda.traineeship.quiz.exceptions.UnauthorizedActionException;
import ru.coda.traineeship.quiz.model.Answer;
import ru.coda.traineeship.quiz.model.Question;

import java.util.List;

public interface AnswerService {
    Answer save(Answer answer) throws UnauthorizedActionException;

    Answer find(Long id) throws ResourceUnavailableException;

    Answer update(Answer newAnswer) throws UnauthorizedActionException, ResourceUnavailableException;

    void delete(Answer answer) throws UnauthorizedActionException, ResourceUnavailableException;

    List<Answer> findAnswersByQuestion(Question question);

    int countAnswersInQuestion(Question question);
}

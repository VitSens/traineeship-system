package ru.coda.traineeship.quiz.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.coda.traineeship.quiz.exceptions.ActionRefusedException;
import ru.coda.traineeship.quiz.exceptions.ResourceUnavailableException;
import ru.coda.traineeship.quiz.exceptions.UnauthorizedActionException;
import ru.coda.traineeship.quiz.model.Answer;
import ru.coda.traineeship.quiz.model.Question;
import ru.coda.traineeship.quiz.repository.AnswerRepository;
import ru.coda.traineeship.quiz.service.abstracts.AnswerService;
import ru.coda.traineeship.quiz.service.abstracts.QuestionService;

import java.util.List;
@Service("AnswerService")
@Transactional
public class AnswerServiceImpl implements AnswerService {
    private static final Logger logger = LoggerFactory.getLogger(AnswerServiceImpl.class);
    private AnswerRepository answerRepository;


    private QuestionService questionService;

    @Autowired
    public AnswerServiceImpl(AnswerRepository answerRepository,@Lazy QuestionService questionService) {
        this.answerRepository = answerRepository;
        this.questionService = questionService;
    }

    @Override
    public Answer find(Long id) throws ResourceUnavailableException {
        if (answerRepository.findById(id).isPresent()) {
            return answerRepository.findById(id).get();
        } else {
            logger.error("Answer " + id + " not found");
            throw new ResourceUnavailableException("Answer " + id + " not found");
        }
    }

    @Override
    public Answer save(Answer answer) throws UnauthorizedActionException {
        return answerRepository.save(answer);
    }

    @Override
    public Answer update(Answer newAnswer) throws ResourceUnavailableException, UnauthorizedActionException {
        Answer currentAnswer = find(newAnswer.getId());

        mergeAnswers(currentAnswer, newAnswer);
        return answerRepository.save(currentAnswer);
    }

    @Override
    public void delete(Answer answer) throws ResourceUnavailableException, UnauthorizedActionException {

        if (questionService.checkIsCorrectAnswer(answer.getQuestion(), answer.getId())) {
            throw new ActionRefusedException("The correct answer can't be deleted");
        }

        answerRepository.delete(answer);
    }

    private void mergeAnswers(Answer currentAnswer, Answer newAnswer) {
        currentAnswer.setText(newAnswer.getText());

        if (newAnswer.getOrder() != null) {
            currentAnswer.setOrder(newAnswer.getOrder());
        }
    }

    @Override
    public List<Answer> findAnswersByQuestion(Question question) {
        return answerRepository.findByQuestionOrderByOrderAsc(question);
    }

    @Override
    public int countAnswersInQuestion(Question question) {
        return answerRepository.countByQuestion(question);
    }
}

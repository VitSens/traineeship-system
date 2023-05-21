package ru.coda.traineeship.quiz.exceptions;

public class ModelVerificationException  extends QuizException{
    private static final long serialVersionUID = 1L;

    public ModelVerificationException() {
        super();
    }

    public ModelVerificationException(String message) {
        super(message);
    }
}

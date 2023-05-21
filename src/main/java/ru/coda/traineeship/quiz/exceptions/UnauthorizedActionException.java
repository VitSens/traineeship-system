package ru.coda.traineeship.quiz.exceptions;

public class UnauthorizedActionException extends QuizException {

	private static final long serialVersionUID = 1L;

	public UnauthorizedActionException() {
		super();
	}

	public UnauthorizedActionException(String message) {
		super(message);
	}
}

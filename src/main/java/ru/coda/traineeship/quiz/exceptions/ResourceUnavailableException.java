package ru.coda.traineeship.quiz.exceptions;

public class ResourceUnavailableException extends QuizException {

	private static final long serialVersionUID = 1L;

	public ResourceUnavailableException() {
		super();
	}

	public ResourceUnavailableException(String message) {
		super(message);
	}
}

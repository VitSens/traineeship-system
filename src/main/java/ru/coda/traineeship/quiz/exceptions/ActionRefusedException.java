package ru.coda.traineeship.quiz.exceptions;

public class ActionRefusedException extends QuizException {

	private static final long serialVersionUID = 1L;

	public ActionRefusedException() {
		super();
	}

	public ActionRefusedException(String message) {
		super(message);
	}
}
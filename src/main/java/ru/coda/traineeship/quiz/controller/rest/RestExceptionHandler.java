package ru.coda.traineeship.quiz.controller.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.coda.traineeship.quiz.controller.util.ErrorInfo;
import ru.coda.traineeship.quiz.exceptions.*;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice("ru.coda.traineeship.quiz.controller.rest")
public class RestExceptionHandler {

	@ExceptionHandler(UnauthorizedActionException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ResponseBody
	public ErrorInfo unauthorizedAction(HttpServletRequest req, Exception ex) {
		return new ErrorInfo(req.getRequestURL().toString(), ex, HttpStatus.UNAUTHORIZED.value());
	}

	@ExceptionHandler(ResourceUnavailableException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ErrorInfo resourceUnavailable(HttpServletRequest req, Exception ex) {
		return new ErrorInfo(req.getRequestURL().toString(), ex, HttpStatus.NOT_FOUND.value());
	}

	@ExceptionHandler(ModelVerificationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorInfo modelVerificationError(HttpServletRequest req, Exception ex) {
		return new ErrorInfo(req.getRequestURL().toString(), ex, HttpStatus.BAD_REQUEST.value());
	}

	@ExceptionHandler(InvalidParametersException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorInfo invalidParametersError(HttpServletRequest req, Exception ex) {
		return new ErrorInfo(req.getRequestURL().toString(), ex, HttpStatus.BAD_REQUEST.value());
	}

	@ExceptionHandler(ActionRefusedException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ResponseBody
	public ErrorInfo actionRefusedError(HttpServletRequest req, Exception ex) {
		return new ErrorInfo(req.getRequestURL().toString(), ex, HttpStatus.FORBIDDEN.value());
	}
}

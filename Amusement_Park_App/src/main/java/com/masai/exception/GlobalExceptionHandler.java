package com.masai.exception;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler { //// this is a single point to handle exception for whole application

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<String> handleInvalidMethodArguments(MethodArgumentNotValidException ex) {
		List<ObjectError> allErrors = ex.getAllErrors();
		List<String> errorsToStringList = MethodArgumentNotValidException.errorsToStringList(allErrors);
		String message = String.join(", ", errorsToStringList);
		return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(InvalidInputException.class)
	public ResponseEntity<ErrorDetails> invalidInputExceptionHandler(InvalidInputException ex, WebRequest request) {
		//Create an object of ErrorDetails Class
		ErrorDetails details = new ErrorDetails(LocalDateTime.now(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<ErrorDetails>(details, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ErrorDetails> notFoundExceptionHandler(NotFoundException ex, WebRequest request) {
		//Create an object of ErrorDetails Class
		ErrorDetails details = new ErrorDetails(LocalDateTime.now(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<ErrorDetails>(details, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ErrorDetails> handleException404(NoHandlerFoundException ex, WebRequest request){
		//Create an object of ErrorDetails
		ErrorDetails details = new ErrorDetails(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<ErrorDetails>(details, HttpStatus.BAD_REQUEST);
	}

}

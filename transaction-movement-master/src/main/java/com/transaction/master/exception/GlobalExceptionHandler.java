package com.transaction.master.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.transaction.master.dto.ResponseStatus;
import com.transaction.master.util.ErrorCodes;

@RestControllerAdvice()
public class GlobalExceptionHandler {

	/**
	 * Handling the GenericException
	 */
	@ExceptionHandler(GenericException.class)
	public ResponseEntity<?> handleGenericException(GenericException exception) {
		return ResponseEntity.ok(exception.getStatus());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		// exception.printStackTrace();

		ResponseStatus response = new ResponseStatus();
		response.setCode(ErrorCodes.ER201.getCode());
		response.setMessage(e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
		return ResponseEntity.badRequest().body(response);
	}

//	@ExceptionHandler(Exception.class)
//	public ResponseEntity<?> handleSystemError(Exception e) {
//		// exception.printStackTrace();
//
//		ResponseStatus response = new ResponseStatus();
//		response.setCode(ErrorCodes.ER203.getCode());
//		response.setCode(ErrorCodes.ER203.getMessage());
////		ResponseStatus response = ResponseStatus.builder().code(ErrorCodes.ER203.getCode())
////				.message(ErrorCodes.ER203.getMessage()).build();
//		return ResponseEntity.ok(response);
//	}

}

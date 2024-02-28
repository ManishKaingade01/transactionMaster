package com.assignment.crud.application.exception;

import com.assignment.crud.application.dto.ResponseStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GenericException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ResponseStatus status;
	public Object getStatus() {
		// TODO Auto-generated method stub
		return null;
	} 

}

package com.assignment.crud.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseStatus {
	
	private String code;
	private String message;
	public static Object builder() {
		// TODO Auto-generated method stub
		return null;
	}
	public void setMessage(String defaultMessage) {
		// TODO Auto-generated method stub
		
	}
	public void setCode(Object code2) {
		// TODO Auto-generated method stub
		
	}

}

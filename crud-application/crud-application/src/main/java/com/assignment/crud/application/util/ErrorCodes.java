package com.assignment.crud.application.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCodes {
	ER201("ER201", "Validation Error :"), 
	ER202("ER202", "Duplicate record found"), 
	ER203("ER203", "System error"),
	ER204("ER204", "Record not found"),
	ER200("200", "Successfully Record deleted id: ");

	private String code;
	private String message;
	ErrorCodes(String string, String string2) {
		// TODO Auto-generated constructor stub
	}
	public Object getCode() {
		// TODO Auto-generated method stub
		return null;
	}

}

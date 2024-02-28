package com.transaction.master.util;



public enum ErrorCodes {
	ER201("ER201", "Validation Error :"), 
	ER202("ER202", "Duplicate record found"), 
	ER203("ER203", "System error"),
	ER204("ER204", "Record not found"),
	ER205("ER205", "Movement Code should not be null or empty"),
	ER200("200", "Successfully Record deleted id: ");

	private String code;
	private String message;
	public String getCode() {
		return code;
	}
	public String getMessage() {
		return message;
	}
	private ErrorCodes(String code, String message) {
		this.code = code;
		this.message = message;
	}
	
	

}

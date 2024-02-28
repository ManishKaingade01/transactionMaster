package com.transaction.master.exception;

import com.transaction.master.dto.ResponseStatus;

public class GenericException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ResponseStatus status;
	public ResponseStatus getStatus() {
		return status;
	}
	
	public GenericException(ResponseStatus status) {
		super();
		this.status = status;
	}

	public void setStatus(ResponseStatus status) {
		this.status = status;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	} 
	
	

}

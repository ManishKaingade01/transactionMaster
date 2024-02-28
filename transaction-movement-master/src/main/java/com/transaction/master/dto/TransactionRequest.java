package com.transaction.master.dto;

import java.util.List;

public class TransactionRequest {
	private String transactionName;
	private String transactionCode;
	private String transactionDescription;
	private List<MovementCodeDto> movementCodes;
	public String getTransactionName() {
		return transactionName;
	}
	public void setTransactionName(String transactionName) {
		this.transactionName = transactionName;
	}
	public String getTransactionCode() {
		return transactionCode;
	}
	public void setTransactionCode(String transactionCode) {
		this.transactionCode = transactionCode;
	}
	public String getTransactionDescription() {
		return transactionDescription;
	}
	public void setTransactionDescription(String transactionDescription) {
		this.transactionDescription = transactionDescription;
	}
	public List<MovementCodeDto> getMovementCodes() {
		return movementCodes;
	}
	public void setMovementCodes(List<MovementCodeDto> movementCodes) {
		this.movementCodes = movementCodes;
	}
	@Override
	public String toString() {
		return "TransactionRequest [transactionName=" + transactionName + ", transactionCode=" + transactionCode
				+ ", transactionDescription=" + transactionDescription + ", movementCodes=" + movementCodes + "]";
	}
	
	

}

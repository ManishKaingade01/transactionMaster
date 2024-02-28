package com.transaction.master.dto;

public class MovementCodeDto {
	private String movementCode;
	private String movementDescription;
	public String getMovementCode() {
		return movementCode;
	}
	public void setMovementCode(String movementCode) {
		this.movementCode = movementCode;
	}
	public String getMovementDescription() {
		return movementDescription;
	}
	public void setMovementDescription(String movementDescription) {
		this.movementDescription = movementDescription;
	}
	public String getMovementEffect() {
		return movementEffect;
	}
	public void setMovementEffect(String movementEffect) {
		this.movementEffect = movementEffect;
	}
	private String movementEffect;
	@Override
	public String toString() {
		return "MovementCode [movementCode=" + movementCode + ", movementDescription=" + movementDescription
				+ ", movementEffect=" + movementEffect + "]";
	}

}

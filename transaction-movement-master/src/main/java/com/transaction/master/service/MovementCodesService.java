package com.transaction.master.service;

import java.util.List;

import com.transaction.master.dto.MovementCodeDto;
import com.transaction.master.dto.ResponseStatus;
import com.transaction.master.entity.MovementCode;

public interface MovementCodesService {
	MovementCode addMovementCode(Integer transactionId, MovementCodeDto request);

	MovementCode getMovementCode(Integer id);

	ResponseStatus deletMovementCode(Integer id);
	
	List<MovementCode> getAllMovementCodes();

}

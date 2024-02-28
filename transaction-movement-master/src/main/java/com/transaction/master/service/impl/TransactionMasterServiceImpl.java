package com.transaction.master.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transaction.master.dto.MovementCodeDto;
import com.transaction.master.dto.ResponseStatus;
import com.transaction.master.dto.TransactionRequest;
import com.transaction.master.entity.MovementCode;
import com.transaction.master.entity.TransactionMaster;
import com.transaction.master.exception.GenericException;
import com.transaction.master.repository.MovementCodeRepository;
import com.transaction.master.repository.TransactionMasterRepository;
import com.transaction.master.service.TransactionMasterService;
import com.transaction.master.util.ErrorCodes;
import com.transaction.master.util.UtilService;

@Service("TransactionMasterService")

public class TransactionMasterServiceImpl implements TransactionMasterService {

	@Autowired
	TransactionMasterRepository transactionMasterRepository;

	@Autowired
	MovementCodeRepository movementCodeRepository;

	@Override
	public TransactionMaster addTransaction(TransactionRequest request) {
		try {

			// Check duplicate Transaction
			checkIfTransactionAlreadyExist(request);

			List<MovementCodeDto> movementCodes = request.getMovementCodes();
			// Verify movement data is present
			if (movementCodes == null || movementCodes.isEmpty()) {
				ResponseStatus status = new ResponseStatus();
				status.setCode(ErrorCodes.ER205.getCode());
				status.setMessage(ErrorCodes.ER205.getMessage());
				throw new GenericException(status);
			}

			// map request to entity
			TransactionMaster transactionMasterEntity = mapRequestToEntity(request);
			return transactionMasterEntity;

		} catch (GenericException e) {
			throw e;
		} catch (Exception e) {
			ResponseStatus status = new ResponseStatus();
			status.setCode(ErrorCodes.ER203.getCode());
			status.setMessage(ErrorCodes.ER203.getMessage());
			throw new GenericException(status);
		}
	}

	private TransactionMaster mapRequestToEntity(TransactionRequest request) {
		TransactionMaster transactionMaster = new TransactionMaster();
		transactionMaster.setTransactionCode(request.getTransactionCode());
		transactionMaster.setTransactionName(request.getTransactionName());
		transactionMaster.setTransactiondiscription(request.getTransactionDescription());
		transactionMaster.setCreatedAt(new Date());
		transactionMaster.setCreatedBy(UtilService.getUUID());
		transactionMaster.setClientId(UtilService.getUUID());

		transactionMaster = this.transactionMasterRepository.save(transactionMaster);
		List<MovementCodeDto> requestMovementCodes = request.getMovementCodes();
		List<MovementCode> movementCodes = new ArrayList<>();

		for (MovementCodeDto movementCode : requestMovementCodes) {
			MovementCode movementCodeEntity = new MovementCode();
			movementCodeEntity.setMovementCode(movementCode.getMovementCode());
			movementCodeEntity.setMovementDescription(movementCode.getMovementDescription());
			movementCodeEntity.setMovementEffect(movementCode.getMovementEffect());
			movementCodeEntity.setCreatedAt(new Date());
			movementCodeEntity.setCreatedBy(UtilService.getUUID());
			movementCodeEntity.setClientId(UtilService.getUUID());
			movementCodeEntity.setTransaction(transactionMaster);
			movementCodes.add(movementCodeEntity);
		}
		movementCodes = (List<MovementCode>) this.movementCodeRepository.save(movementCodes);
		transactionMaster.setMovementCodes(movementCodes);
		transactionMaster = mapEntityToFinalResponse(transactionMaster);
		return transactionMaster;
	}

	private TransactionMaster mapEntityToFinalResponse(TransactionMaster transactionMaster) {
		List<MovementCode> movementCodes = new ArrayList<>();
		for (MovementCode movement : transactionMaster.getMovementCodes()) {
			MovementCode movementCodeEntity = new MovementCode();
			movementCodeEntity.setId(movement.getId());
			movementCodeEntity.setMovementCode(movement.getMovementCode());
			movementCodeEntity.setMovementDescription(movement.getMovementDescription());
			movementCodeEntity.setMovementEffect(movement.getMovementEffect());
			movementCodeEntity.setCreatedAt(movement.getCreatedAt());
			movementCodeEntity.setCreatedBy(movement.getCreatedBy());
			movementCodeEntity.setClientId(movement.getClientId());
			movementCodes.add(movementCodeEntity);

		}
		transactionMaster.setMovementCodes(movementCodes);

		return transactionMaster;
	}

	private void checkIfTransactionAlreadyExist(TransactionRequest request) {
		TransactionMaster transactionMasterEntity = transactionMasterRepository
				.findByTransactionCode(request.getTransactionCode());
		if (transactionMasterEntity != null) {
			ResponseStatus status = new ResponseStatus();
			status.setCode(ErrorCodes.ER202.getCode());
			status.setMessage(ErrorCodes.ER202.getMessage());
			throw new GenericException(status);
		}
	}

	@Override
	public TransactionMaster getTransaction(int id) {
		try {

			ResponseStatus status = new ResponseStatus();
			status.setCode(ErrorCodes.ER204.getCode());
			status.setMessage(ErrorCodes.ER204.getMessage());

			TransactionMaster transactionMaster = Optional.ofNullable(this.transactionMasterRepository.findById(id))
					.orElseThrow(() -> new GenericException(status));

			return mapEntityToFinalResponse(transactionMaster);
		} catch (GenericException e) {
			throw e;
		} catch (Exception e) {
			ResponseStatus status = new ResponseStatus();
			status.setCode(ErrorCodes.ER203.getCode());
			status.setMessage(ErrorCodes.ER203.getMessage());
			throw new GenericException(status);
		}
	}

	@Override
	public ResponseStatus deleteTransaction(int id) {
		this.transactionMasterRepository.delete(id);
		ResponseStatus status = new ResponseStatus();
		status.setCode(ErrorCodes.ER200.getCode());
		status.setMessage(ErrorCodes.ER200.getMessage() + id);
		return status;
	}

}

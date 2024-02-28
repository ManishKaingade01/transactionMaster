package com.transaction.master.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transaction.master.dto.MovementCodeDto;
import com.transaction.master.dto.ResponseStatus;
import com.transaction.master.entity.MovementCode;
import com.transaction.master.entity.TransactionMaster;
import com.transaction.master.exception.GenericException;
import com.transaction.master.repository.MovementCodeRepository;
import com.transaction.master.repository.TransactionMasterRepository;
import com.transaction.master.service.MovementCodesService;
import com.transaction.master.util.ErrorCodes;
import com.transaction.master.util.UtilService;

@Service("MovementCodesService")
public class MovementCodesServiceImpl implements MovementCodesService {

	@Autowired
	private MovementCodeRepository movementCodeRepository;

	@Autowired
	private TransactionMasterRepository transactionMasterRepository;

	@Override
	public MovementCode addMovementCode(Integer transactionId, MovementCodeDto request) {
		ResponseStatus status = new ResponseStatus();
		status.setCode(ErrorCodes.ER204.getCode());
		status.setMessage(ErrorCodes.ER204.getMessage());

		TransactionMaster transactionMaster = Optional
				.ofNullable(this.transactionMasterRepository.findOne(transactionId))
				.orElseThrow(() -> new GenericException(status));

		Optional<MovementCode> movementCode = Optional
				.ofNullable(this.movementCodeRepository.findByMovementCode(request.getMovementCode()));

		if (movementCode.isPresent()) {
			status.setCode(ErrorCodes.ER202.getCode());
			status.setMessage(ErrorCodes.ER202.getMessage());
			throw new GenericException(status);

		}
		return saveMovementCode(transactionMaster, request);
	}

	private MovementCode saveMovementCode(TransactionMaster transactionMaster, MovementCodeDto request) {
		MovementCode movementCodeEntity = new MovementCode();
		movementCodeEntity.setMovementCode(request.getMovementCode());
		movementCodeEntity.setMovementDescription(request.getMovementDescription());
		movementCodeEntity.setMovementEffect(request.getMovementEffect());
		movementCodeEntity.setCreatedAt(new Date());
		movementCodeEntity.setCreatedBy(UtilService.getUUID());
		movementCodeEntity.setClientId(UtilService.getUUID());
		movementCodeEntity.setTransaction(transactionMaster);

		// add movement
		movementCodeEntity = this.movementCodeRepository.save(movementCodeEntity);
		movementCodeEntity.setTransaction(null);
		return movementCodeEntity;

	}

	@Override
	public MovementCode getMovementCode(Integer id) {
		try {
			ResponseStatus status = new ResponseStatus();
			status.setCode(ErrorCodes.ER204.getCode());
			status.setMessage(ErrorCodes.ER204.getMessage());

			MovementCode movementCode = Optional.ofNullable(this.movementCodeRepository.findOne(id))
					.orElseThrow(() -> new GenericException(status));
			movementCode.setTransaction(null);

			return movementCode;
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
	public ResponseStatus deletMovementCode(Integer id) {
		MovementCode movementCodes = movementCodeRepository.findOne(id);
		if (movementCodes != null) {
			TransactionMaster transactionMaster = movementCodes.getTransaction();
			transactionMaster.getMovementCodes().remove(movementCodes);

			this.transactionMasterRepository.save(transactionMaster);

		}
		ResponseStatus status = new ResponseStatus();
		status.setCode(ErrorCodes.ER200.getCode());
		status.setMessage(ErrorCodes.ER200.getMessage() + id);
		return status;
	}

	@Override
	public List<MovementCode> getAllMovementCodes() {
		return movementCodeRepository.findAll().stream().map(item -> {
			item.setTransaction(null);
			return item;
		}).collect(Collectors.toList());
	}

}

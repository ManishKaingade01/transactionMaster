package com.transaction.master.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.transaction.master.dto.MovementCodeDto;
import com.transaction.master.service.MovementCodesService;

@RestController
@RequestMapping("/v1/")
public class MovementCodesController {

	@Autowired
	private MovementCodesService movementCodesService;

	@PostMapping(value = "/transactions/{transactionId}/addMovementCode", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addMovementCode(@PathVariable("transactionId") Integer transactionId,
			@RequestBody MovementCodeDto request) {
		return ResponseEntity.ok(movementCodesService.addMovementCode(transactionId, request));
	}

	@GetMapping(value = "/movementcodes/{movementCodeId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getMovementCode(@PathVariable("movementCodeId") int movementCodeId) {
		return ResponseEntity.ok(movementCodesService.getMovementCode(movementCodeId));
	}

	@GetMapping(value = "/movementcodes", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllMovementCodes() {
		return ResponseEntity.ok(movementCodesService.getAllMovementCodes());
	}

	@DeleteMapping(value = "/movementcodes/{movementCodeId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteMovementCode(@PathVariable("movementCodeId") int movementCodeId) {
		return ResponseEntity.ok(movementCodesService.deletMovementCode(movementCodeId));
	}

}

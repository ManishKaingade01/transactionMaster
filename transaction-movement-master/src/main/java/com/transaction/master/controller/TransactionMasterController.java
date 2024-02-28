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

import com.transaction.master.dto.TransactionRequest;
import com.transaction.master.service.TransactionMasterService;

@RestController
@RequestMapping("/v1/transaction/")
public class TransactionMasterController {

	@Autowired
	private TransactionMasterService transactionMasterService;

	@PostMapping(value = "/addTransaction", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addTransaction(@RequestBody TransactionRequest request) {
		return ResponseEntity.ok(transactionMasterService.addTransaction(request));
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getTransaction(@PathVariable int id) {
		return ResponseEntity.ok(transactionMasterService.getTransaction(id));
	}

	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteTransaction(@PathVariable int id) {
		return ResponseEntity.ok(transactionMasterService.deleteTransaction(id));
	}

}

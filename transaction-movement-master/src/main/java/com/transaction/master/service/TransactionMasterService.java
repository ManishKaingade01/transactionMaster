package com.transaction.master.service;

import com.transaction.master.dto.ResponseStatus;
import com.transaction.master.dto.TransactionRequest;
import com.transaction.master.entity.TransactionMaster;

public interface TransactionMasterService {
	TransactionMaster addTransaction(TransactionRequest request);
	TransactionMaster getTransaction(int id);
	ResponseStatus deleteTransaction(int id);

}

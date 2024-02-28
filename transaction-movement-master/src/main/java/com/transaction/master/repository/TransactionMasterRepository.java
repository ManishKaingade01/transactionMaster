package com.transaction.master.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.transaction.master.entity.TransactionMaster;

@Repository
public interface TransactionMasterRepository extends CrudRepository<TransactionMaster, Integer> {
	TransactionMaster findByTransactionCode(String transactionCode);
	
	TransactionMaster findById(Integer id);

}

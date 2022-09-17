package com.simplebanking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simplebanking.model.TransactionType;
import com.simplebanking.repository.TransactionTypeRepository;

@Service
public class TransactionTypeService {

	@Autowired
	TransactionTypeRepository transactionTypeRepository;

	public void saveTransactionType(TransactionType transactionType) {
		transactionTypeRepository.save(transactionType);
	}

	public List<TransactionType> getAllTransactionTypes() {
		return transactionTypeRepository.findAll();
	}

	public TransactionType getTransactionTypeByName(String transactionType) {
		return transactionTypeRepository.findByName(transactionType).orElse(null);
	}

}

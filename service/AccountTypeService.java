package com.simplebanking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simplebanking.model.AccountType;
import com.simplebanking.repository.AccountTypeRepository;

@Service
public class AccountTypeService {

	@Autowired
	AccountTypeRepository accountTypeRepository;
	
	public void saveAccountType(AccountType accountType) {
		accountTypeRepository.save(accountType);
	}

	public List<AccountType> getAllAccountTypes() {
		return accountTypeRepository.findAll();
	}

}

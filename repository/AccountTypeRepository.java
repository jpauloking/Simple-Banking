package com.simplebanking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simplebanking.model.AccountType;

public interface AccountTypeRepository extends JpaRepository<AccountType, Integer> {

}

package com.simplebanking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simplebanking.model.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {

	Optional<Account> findByAccountName(String accountName);

	Optional<Account> findByAccountNumber(String accountNumber);

}

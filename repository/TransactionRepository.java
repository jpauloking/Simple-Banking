package com.simplebanking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simplebanking.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

}

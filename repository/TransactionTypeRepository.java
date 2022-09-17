package com.simplebanking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simplebanking.model.TransactionType;

public interface TransactionTypeRepository extends JpaRepository<TransactionType, Integer> {

	Optional<TransactionType> findByName(String name);

}

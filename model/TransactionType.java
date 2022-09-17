package com.simplebanking.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class TransactionType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "name")
	private String name;
	@Column(name = "description")
	private String description;
	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, mappedBy = "transactionType", orphanRemoval = false)
	private Set<Transaction> transactions = new HashSet<>();

	public TransactionType() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(Set<Transaction> transactions) {
		this.transactions = transactions;
	}
	
}

package com.simplebanking.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "amount")
	private double amount;
	@Column(name = "reference_code")
	private String referenceCode;
	@Column(name = "status")
	private String status; // failed //successful
	@Column(name = "reason_code")
	private String reasonCode;
	@Column(name = "created_at")
	private Date createdAt;
	@Column(name = "destination_account_name", nullable = true)
	private String destinationAccountName;
	@Column(name = "destination_account_number", nullable = true)
	private String destinationAccountNumber;
	@ManyToOne
	private Account account;
	@ManyToOne
	private TransactionType transactionType;

	public Transaction() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getReferenceCode() {
		return referenceCode;
	}

	public void setReferenceCode(String referenceCode) {
		this.referenceCode = referenceCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getDestinationAccountName() {
		return destinationAccountName;
	}

	public void setDestinationAccountName(String destinationAccountName) {
		this.destinationAccountName = destinationAccountName;
	}

	public String getDestinationAccountNumber() {
		return destinationAccountNumber;
	}

	public void setDestinationAccountNumber(String destinationAccountNumber) {
		this.destinationAccountNumber = destinationAccountNumber;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}
}

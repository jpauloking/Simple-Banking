package com.simplebanking.util;

import com.simplebanking.model.Account;

public class DepositTransactionImpl implements Transaction {

	private Account account;

	public DepositTransactionImpl(Account account) {
		this.account = account;
	}

	@Override
	public double handleTransaction(double transactionAmount) {
		double endingAmount = account.getAccountBalance() + transactionAmount;
		account.setAccountBalance(endingAmount);
		return endingAmount;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}

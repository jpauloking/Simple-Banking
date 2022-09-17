package com.simplebanking.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simplebanking.model.Account;
import com.simplebanking.repository.AccountRepository;
import com.simplebanking.util.DepositTransactionImpl;
import com.simplebanking.util.WithdrawalTransactionImpl;

@Service
public class AccountService {
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	UserService userService;

	public void saveAccount(Account account) {
		account.setUser(userService.getPrincipalUser());
		if (account.getCreatedAt() == null && account.getId() == null) {
			account.setCreatedAt(new Date());
		}
		if (account.getAccountNumber().isBlank() || account.getAccountNumber() == null
				|| account.getAccountNumber().isEmpty()) {
			String salt = UUID.randomUUID().toString().substring(0, 5);
			account.setAccountNumber("ACC-" + Calendar.YEAR + "" + Calendar.MONTH + "-" + salt);
		}
		account.setUpdatedAt(new Date());
		accountRepository.save(account);
	}

	public List<Account> getAllAccounts() {
		return accountRepository.findAll();
	}

	public Account getAccountById(Integer accountId) {
		return accountRepository.findById(accountId).orElse(null);
	}

	public void deleteAccountById(Integer accountId) {
		accountRepository.deleteById(accountId);
	}

	public double deposit(Account account, double amount) {
		DepositTransactionImpl depositTransactionImpl = new DepositTransactionImpl(account);
		double endAmount = depositTransactionImpl.handleTransaction(amount);
		accountRepository.save(account);
		return endAmount;
	}

	public double withdraw(Account account, double amount) {
		WithdrawalTransactionImpl withdrawalTransactionImpl = new WithdrawalTransactionImpl(account);
		double endAmount = withdrawalTransactionImpl.handleTransaction(amount);
		accountRepository.save(account);
		return endAmount;
	}

	public double transfer(Account sourceAccount, Account destinationAcount, double amount) {
		double amountWithdrawn = withdraw(sourceAccount, amount);
		double amountDeposited = deposit(destinationAcount, amountWithdrawn);
		return amountDeposited;
	}

	public double getTotalBalance(List<Account> accounts) {
		double totalBalance = 0;
		for (Account account : accounts) {
			totalBalance += account.getAccountBalance();
		}
		return totalBalance;
	}

	public Account getAccountByAccountName(String accountName) {
		return accountRepository.findByAccountName(accountName).orElse(null);
	}

	public Account getAccountByAccountNumber(String accountNumber) {
		return accountRepository.findByAccountNumber(accountNumber).orElse(null);
	}
}

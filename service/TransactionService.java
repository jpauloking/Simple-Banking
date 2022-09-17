package com.simplebanking.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simplebanking.model.Transaction;
import com.simplebanking.repository.TransactionRepository;

@Service
public class TransactionService {

	@Autowired
	TransactionRepository transactionRepository;
	@Autowired
	AccountService accountService;

	public void saveTransaction(Transaction transaction) {
		if (transaction.getCreatedAt() == null) {
			transaction.setCreatedAt(new Date());
		}
		transaction.setReferenceCode(UUID.randomUUID().toString());
		transaction.setStatus(transact(transaction) ? "Successful" : "Unsuccessful");
		transactionRepository.save(transaction);
	}

	public List<Transaction> getAllTransactions() {
		return transactionRepository.findAll();
	}

	private boolean transact(Transaction transaction) {
		double result;
		switch (transaction.getTransactionType().getId()) {
		case 3:
			result = accountService.transfer(transaction.getAccount(),
					accountService.getAccountByAccountName(transaction.getDestinationAccountName()),
					transaction.getAmount());
			break;
		case 2:
			result = accountService.deposit(transaction.getAccount(), transaction.getAmount());
			break;
		case 1:
			result = accountService.withdraw(transaction.getAccount(), transaction.getAmount());
			break;
		default:
			result = -1;
		}
		return result != -1;
	}

	private List<Number> getTotalIncome(List<Transaction> transactions) {
		double totalIncome = 0;
		int incomeCount = 0;
		for (Transaction transaction : transactions) {
			if (transaction.getTransactionType().getId() == 2) {
				totalIncome += transaction.getAmount();
				incomeCount++;
			}
		}
		return List.of(totalIncome, incomeCount);
	}

	private List<Number> getTotalExpenditure(List<Transaction> transactions) {
		double totalExpenditure = 0;
		int expenditureCount = 0;
		for (Transaction transaction : transactions) {
			if (transaction.getTransactionType().getId() == 1) {
				totalExpenditure += transaction.getAmount();
				expenditureCount++;
			}
		}
		return List.of(totalExpenditure, expenditureCount);
	}

	public Totals getTotals() {
		List<Transaction> allTransactions = getAllTransactions();
		List<Number> totalIncome = getTotalIncome(allTransactions);
		List<Number> totalExpenditure = getTotalExpenditure(allTransactions);
		List<Number> totalTransfer = getTotalTransfer(allTransactions);
		return new Totals(totalIncome.get(0).doubleValue(), totalExpenditure.get(0).doubleValue(),
				totalTransfer.get(0).doubleValue(), totalIncome.get(1).intValue(), totalExpenditure.get(1).intValue(),
				totalTransfer.get(1).intValue());
	}

	private List<Number> getTotalTransfer(List<Transaction> transactions) {
		double totalTransfer = 0;
		int transferCount = 0;
		for (Transaction transaction : transactions) {
			if (transaction.getTransactionType().getId() == 3) {
				totalTransfer += transaction.getAmount();
				transferCount++;
			}
		}
		return List.of(totalTransfer, transferCount);
	}

	public class Totals {
		private double totalIncomes, totalExpenditures, totalTransfers;
		private int incomesCount, expendituresCount, transferCount;

		public Totals(double totalIncomes, double totalExpenditures, double totalTransfers, int incomesCount,
				int expendituresCount, int transferCount) {
			super();
			this.totalIncomes = totalIncomes;
			this.totalExpenditures = totalExpenditures;
			this.totalTransfers = totalTransfers;
			this.incomesCount = incomesCount;
			this.expendituresCount = expendituresCount;
			this.transferCount = transferCount;
		}

		public double getTotalIncomes() {
			return totalIncomes;
		}

		public void setTotalIncomes(double totalIncomes) {
			this.totalIncomes = totalIncomes;
		}

		public double getTotalExpenditures() {
			return totalExpenditures;
		}

		public void setTotalExpenditures(double totalExpenditures) {
			this.totalExpenditures = totalExpenditures;
		}

		public double getTotalTransfers() {
			return totalTransfers;
		}

		public void setTotalTransfers(double totalTransfers) {
			this.totalTransfers = totalTransfers;
		}

		public int getIncomesCount() {
			return incomesCount;
		}

		public void setIncomesCount(int incomesCount) {
			this.incomesCount = incomesCount;
		}

		public int getExpendituresCount() {
			return expendituresCount;
		}

		public void setExpendituresCount(int expendituresCount) {
			this.expendituresCount = expendituresCount;
		}

		public int getTransferCount() {
			return transferCount;
		}

		public void setTransferCount(int transferCount) {
			this.transferCount = transferCount;
		}

	}
}

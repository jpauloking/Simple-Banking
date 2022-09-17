package com.simplebanking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.simplebanking.model.Transaction;
import com.simplebanking.service.AccountService;
import com.simplebanking.service.TransactionService;
import com.simplebanking.service.TransactionTypeService;

@Controller
@RequestMapping("/transactions")
public class TransactionController {
	@Autowired
	AccountService accountService;
	@Autowired
	TransactionService transactionService;
	@Autowired
	TransactionTypeService transactionTypeService;
	
	@GetMapping
	public String getTransactionListView(ModelMap model) {
		model.addAttribute("transactions", transactionService.getAllTransactions());
		return "transaction/transaction_list_view";
	}

	@GetMapping("/transaction-form")
	public String getTransaction(ModelMap model) {
		model.addAttribute("transaction", new Transaction());
		model.addAttribute("accounts", accountService.getAllAccounts());
		model.addAttribute("transactionTypes", transactionTypeService.getAllTransactionTypes());
		model.addAttribute("selectedTransactionType", null);
		model.addAttribute("selectedAccount", null);
		return "transaction/transaction_form_view";
	}
	
	@PostMapping("/transaction-form")
	public String postTransaction(Transaction transaction) {
		transactionService.saveTransaction(transaction);
		return "redirect:/transactions";
	}

}

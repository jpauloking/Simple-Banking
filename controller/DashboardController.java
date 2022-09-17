package com.simplebanking.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.simplebanking.model.Account;
import com.simplebanking.service.AccountService;
import com.simplebanking.service.TransactionService;
import com.simplebanking.service.TransactionService.Totals;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {
	@Autowired
	AccountService accountService;
	@Autowired
	TransactionService transactionService;

	@GetMapping
	public String getDashboardView(ModelMap model) {
		List<Account> accounts = accountService.getAllAccounts();
		Totals totals = transactionService.getTotals();
		model.addAttribute("accounts", accountService.getAllAccounts());
		model.addAttribute("totalBalance", accountService.getTotalBalance(accounts));
		model.addAttribute("totalIncome", totals.getTotalIncomes());
		model.addAttribute("totalExpenditure", totals.getTotalExpenditures());
		model.addAttribute("totalTransfer", totals.getTotalTransfers());
		model.addAttribute("incomeCount", totals.getIncomesCount());
		model.addAttribute("expenditureCount", totals.getExpendituresCount());
		model.addAttribute("transferCount", totals.getTransferCount());
		model.addAttribute("today", new Date());
		return "dashboard_view";
	}

}

package com.simplebanking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.simplebanking.model.Account;
import com.simplebanking.model.AccountType;
import com.simplebanking.model.Transaction;
import com.simplebanking.model.TransactionType;
import com.simplebanking.service.AccountService;
import com.simplebanking.service.AccountTypeService;
import com.simplebanking.service.TransactionTypeService;

@Controller
@RequestMapping("/accounts")
public class AccountController {

	@Autowired
	AccountTypeService accountTypeService;
	@Autowired
	AccountService accountService;
	@Autowired
	TransactionTypeService transactionTypeService;

	@GetMapping
	public String getUserAccounts() {
		return null;
	}

	@GetMapping("/account-form")
	public String getUserAccount(ModelMap model) {
		model.addAttribute("account", new Account());
		List<AccountType> accountTypes = accountTypeService.getAllAccountTypes();
		model.addAttribute("accountTypes", accountTypes);
		model.addAttribute("selectedValue", null);
		return "account/account_form_view";
	}

	@PostMapping("/account-form")
	public String postUserAccount(Account account) {
		accountService.saveAccount(account);
		return "redirect:/dashboard";
	}

	@GetMapping("/{accountId}")
	public String getAccountDetailsView(@PathVariable("accountId") Integer accountId, ModelMap model) {
		Account account = accountService.getAccountById(accountId);
		model.addAttribute("account", account);
		return "account/account_details_view";
	}

	@GetMapping("/{accountId}/update")
	public String getAccountFormView(@PathVariable("accountId") Integer accountId, ModelMap model) {
		Account account = accountService.getAccountById(accountId);
		model.addAttribute("account", account);
		List<AccountType> accountTypes = accountTypeService.getAllAccountTypes();
		model.addAttribute("accountTypes", accountTypes);
		model.addAttribute("selectedValue", account.getId());
		return "account/account_form_view";
	}

	@GetMapping("/{accountId}/delete")
	public String getAccountConfirmDeleteView(@PathVariable("accountId") Integer accountId, ModelMap model) {
		Account account = accountService.getAccountById(accountId);
		model.addAttribute("account", account);
		return "account/account_confirm_delete_view";
	}

	@PostMapping("/{accountId}/delete")
	public String postAccountConfirmDeleteView(@PathVariable("accountId") Integer accountId) {
		accountService.deleteAccountById(accountId);
		return "redirect:/dashboard";
	}

	@GetMapping("/{accountId}/{transactionType}")
	public String getTransaction(@PathVariable("accountId") Integer accountId,
			@PathVariable("transactionType") String transactionType, ModelMap model) {
		Account selectedAccount = accountService.getAccountById(accountId);
		TransactionType _transactionType = transactionTypeService.getTransactionTypeByName(transactionType);
		Transaction transaction = new Transaction();
		transaction.setAccount(selectedAccount);
		transaction.setTransactionType(_transactionType);
		model.addAttribute("transaction", transaction);
		model.addAttribute("transactionTypes", transactionTypeService.getAllTransactionTypes());
		model.addAttribute("accounts", accountService.getAllAccounts());
		model.addAttribute("selectedTransactionType", _transactionType);
		model.addAttribute("selectedAccount", selectedAccount);
		return "transaction/transaction_form_view";
	}
}

package com.simplebanking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.simplebanking.model.AccountType;
import com.simplebanking.service.AccountTypeService;

@Controller
@RequestMapping("/accounts/account-types")
public class AccountTypeController {
	@Autowired
	AccountTypeService accountTypeService;

	@GetMapping("/account-type-form")
	public String getAccountTypeFormView(ModelMap model) {
		model.addAttribute("accountType", new AccountType());
		return "account/account_type/account_type_form_view";
	}
	
	@PostMapping("/account-type-form")
	public String postAccountTypeFormView(AccountType accountType) {
		accountTypeService.saveAccountType(accountType);
		return "redirect:/dashboard";
	}

}

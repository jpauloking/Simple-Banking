package com.simplebanking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.simplebanking.model.TransactionType;
import com.simplebanking.service.TransactionTypeService;

@Controller
@RequestMapping("transactions/transaction-types")
public class TransactionTypeController {
	@Autowired
	TransactionTypeService transactionTypeService;
	
	@GetMapping("/transaction-type-form")
	public String getAccountTypeFormView(ModelMap model) {
		model.addAttribute("transactionType", new TransactionType());
		return "transaction/transaction_type/transaction_type_form_view";
	}
	
	@PostMapping("/transaction-type-form")
	public String postAccountTypeFormView(TransactionType transactionType) {
		transactionTypeService.saveTransactionType(transactionType);
		return "redirect:/dashboard";
	}
}

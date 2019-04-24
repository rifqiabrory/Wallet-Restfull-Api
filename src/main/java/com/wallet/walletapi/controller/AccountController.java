package com.wallet.walletapi.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import com.wallet.walletapi.dto.CommonResponse;
import com.wallet.walletapi.exception.NotFoundException;
import com.wallet.walletapi.interfaces.IAccount;
import com.wallet.walletapi.model.Account;

/**
*   @RIFQIABRORY
*   web developer
*   enigma batch II
* */
@RestController
@RequestMapping("api")
@CrossOrigin
public class AccountController {
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);
	
	public static final String URl_REQUEST_ACCOUNT = "account"; //create
	public static final String URl_REQUEST_ACCOUNT_LIST_BY_CUSTOMER_NUMBER = "accounts/{customer_number}";//read
	public static final String URl_REQUEST_ACCOUNT_BY_ID = "account/{account_number}";//Get By
	
	@Autowired
	private IAccount ia;
	
	@CrossOrigin
	@PostMapping(value=URl_REQUEST_ACCOUNT)
	public CommonResponse<Account> createAccount(@RequestBody Account account) {
		CommonResponse<Account> acc = new CommonResponse<>();
		Account accnt = ia.saveAccount(account);
		acc.setData(accnt);
		return acc;
	}
	
	@CrossOrigin
	@GetMapping(value=URl_REQUEST_ACCOUNT_BY_ID, produces=MediaType.APPLICATION_JSON_VALUE)
	public CommonResponse<Account> getAccountById(@PathVariable (name = "account_number") int account_number) throws NotFoundException {
		Account account = ia.getById(account_number);
		CommonResponse<Account> resp = new CommonResponse<>();
		if (account==null) {
			LOGGER.error(String.format("Account ID %d not found", account_number));
			throw new NotFoundException("444", String.format("Account ID %d not found", account_number));
		} else {
			resp.setData(account);
		}
		return resp;
	}
	
	@CrossOrigin
	@DeleteMapping(value=URl_REQUEST_ACCOUNT_BY_ID)
	public CommonResponse<Account> deleteAccount(@PathVariable (name = "account_number") int account_number) throws NotFoundException {
		Account check = ia.getById(account_number);
		CommonResponse<Account> resp = new CommonResponse<>();
		if (check==null) {
			throw new NotFoundException("444", String.format("Account ID %d not found", account_number));
		} else {
			resp.setData(ia.delete(check));
		}
		return resp;
	}
	
	@CrossOrigin
	@GetMapping(value=URl_REQUEST_ACCOUNT_LIST_BY_CUSTOMER_NUMBER)
	public CommonResponse<List<Account>> getAccountList(@PathVariable (name="customer_number") int customerNumber) throws NotFoundException {
		List<Account> accounts = ia.getListAccount(customerNumber);
		CommonResponse<List<Account>> resp = new CommonResponse<>();
		if (!accounts.isEmpty()) {
			resp.setData(accounts);
		} else {
			throw new NotFoundException("444", "Accounts doesn't exist!");
		}
		return resp;
	}

	@CrossOrigin
	@PutMapping(value=URl_REQUEST_ACCOUNT_BY_ID)
	public CommonResponse<Account> updateAccountBy(@RequestBody Account account) throws NotFoundException {
		Account check = ia.getById(account.getAccountNumber());
		CommonResponse<Account> resp = new CommonResponse<>();
		if (check==null) {
			throw new NotFoundException("444", "Account doesn't exist!");
		} else {
			ia.saveAccount(account);
			resp.setData(account);
		}
		return resp;
	}
}

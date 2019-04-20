package com.wallet.walletapi.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wallet.walletapi.dto.CommonResponse;
import com.wallet.walletapi.exception.NotFoundException;
import com.wallet.walletapi.interfaces.IAccount;
import com.wallet.walletapi.interfaces.ITransaction;
import com.wallet.walletapi.model.Transaction;

@RestController
@RequestMapping("api")
@CrossOrigin
public class TransactionController {
	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionController.class);
	
	public static final String URL_REQUEST_TRANSACTION="transaction";
	public static final String URL_REQUEST_TRANSACTION_LIST="transactions";
	public static final String URL_REQUEST_TRANSACTION_LIST1="transaction1";
	public static final String URL_REQUEST_TRANSACTION_BY_ID="transaction/{id}";
	public static final String URL_REQUEST_TRANSACTION_TOPUP="transaction/topup";
	public static final String URL_REQUEST_TRANSACTION_TRANSFER="transaction/transfer";
	public static final String URL_REQUEST_TRANSACTION_WITHDRAW="transaction/withdraw";
	
	@Autowired
	private ITransaction it;
	
	@Autowired
	private IAccount ia;
	
	@CrossOrigin
	@GetMapping(URL_REQUEST_TRANSACTION_BY_ID)
	public CommonResponse<Transaction> getCustomerEntity(@PathVariable(name="id") int account_number) throws NotFoundException {

		Transaction transaction = it.getById(account_number);
		CommonResponse<Transaction> resp = new CommonResponse<>();
		if (transaction==null) {
			LOGGER.error(String.format("Customer ID %d not found", account_number));
			throw new NotFoundException("101", String.format("Customer ID %d not found", account_number));
		} else {
			resp.setData(transaction);
		}
	return resp;
	}
	
	@CrossOrigin
	@GetMapping(value=URL_REQUEST_TRANSACTION_LIST1)
	public CommonResponse<List<Transaction>> getTransactionList(@RequestParam(name="accountNumber", defaultValue="") int accountNumber) {
		List<Transaction> list;
		list = it.getListTransaction1(accountNumber);
		
		CommonResponse<List<Transaction>> resp = new CommonResponse<>();
		resp.setData(list);
		return resp;
	}
	
	@CrossOrigin
	@PostMapping (value=URL_REQUEST_TRANSACTION_TOPUP)
	public CommonResponse<Transaction> createTopup(@RequestBody Transaction transaction) {
		CommonResponse<Transaction> cust = new CommonResponse<>();
		double balance =ia.getBalance(transaction.getAnCredit());
		double result = balance+transaction.getAmount();
		Transaction cst = it.topUp(transaction);
		cust.setData(cst);
		ia.updateBalance(transaction.getAnCredit(), result);
		return cust;
	}
	
	@CrossOrigin
	@PostMapping (value=URL_REQUEST_TRANSACTION_TRANSFER)
	public CommonResponse<Transaction> createTransfer(@RequestBody Transaction transaction) {
		CommonResponse<Transaction> cust = new CommonResponse<>();
		double balanceCredit =ia.getBalance(transaction.getAnCredit());
		double resultCredit = balanceCredit+transaction.getAmount();
		double balanceDebit =ia.getBalance(transaction.getAnDebit());
		double resultDebit = balanceDebit-transaction.getAmount();
		Transaction cst = it.transfer(transaction);
		cust.setData(cst);
		ia.updateBalance(transaction.getAnCredit(), resultCredit);
		ia.updateBalance(transaction.getAnDebit(), resultDebit);
		return cust;
	}
	
	@CrossOrigin
	@PostMapping (value=URL_REQUEST_TRANSACTION_WITHDRAW)
	public CommonResponse<Transaction> createWithdraw(@RequestBody Transaction transaction) {
		CommonResponse<Transaction> cust = new CommonResponse<>();
		double balance =ia.getBalance(transaction.getAnDebit());
		double result = balance-transaction.getAmount();
		Transaction cst = it.widthraw(transaction);
		cust.setData(cst);
		ia.updateBalance(transaction.getAnDebit(), result);
		return cust;
	}
	
}

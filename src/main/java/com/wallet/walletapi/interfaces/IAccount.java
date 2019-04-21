package com.wallet.walletapi.interfaces;

import java.util.List;

import com.wallet.walletapi.exception.NotFoundException;
import com.wallet.walletapi.model.Account;

public interface IAccount {
	List<Account> getListAccount(int customerNumber);
	Account getById(int accountNumber) throws NotFoundException;
	Account saveAccount(Account account);
	Account delete(Account account);
	double getBalance(int anCredit);
	Account updateBalance(int anCredit, double result);
	
}

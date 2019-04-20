package com.wallet.walletapi.interfaces;

import java.util.List;

import com.wallet.walletapi.exception.NotFoundException;
import com.wallet.walletapi.model.Account;
import com.wallet.walletapi.model.Transaction;

public interface ITransaction {
	List<Transaction> getListTransaction();
	List<Transaction> getListTransaction1(int accountNumber);
	Transaction getById(int idTransaction) throws NotFoundException;
	Transaction topUp(Transaction transaction);
	Transaction transfer(Transaction transaction);
	Transaction widthraw(Transaction transaction);
	//Transaction update(Transaction transaction);
	//Transaction delete(Transaction idTransaction);
	
}

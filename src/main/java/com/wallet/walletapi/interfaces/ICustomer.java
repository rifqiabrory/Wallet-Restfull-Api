package com.wallet.walletapi.interfaces;

import java.util.List;

import com.wallet.walletapi.exception.NotFoundException;
import com.wallet.walletapi.model.Customer;


public interface ICustomer {
	List<Customer> getListCustomer();
	Customer getById(int customerNumber) throws NotFoundException;
	Customer saveCustomer(Customer customer);
	Customer updateCustomer(Customer customer);
	Customer delete(Customer customer);
	Customer getByUsernamePassword(String username, String password) ;
}
package com.wallet.walletapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.wallet.walletapi.model.Account;



public interface AccountRepository extends CrudRepository<Account, Integer>{
	
	@Query("FROM Account where customer.customerNumber= : customerNumber")
	List<Account> getCustemerAccount(@Param("customerNumber") int customerNumber);
}

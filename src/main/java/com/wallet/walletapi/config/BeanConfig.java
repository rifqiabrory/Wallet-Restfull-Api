package com.wallet.walletapi.config;

import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;

import com.wallet.walletapi.impl.AccountImpl;
import com.wallet.walletapi.impl.CustomerImpl;
import com.wallet.walletapi.impl.TransactionImpl;
import com.wallet.walletapi.impl.WalletImpl;
import com.wallet.walletapi.interfaces.IAccount;
import com.wallet.walletapi.interfaces.ICustomer;
import com.wallet.walletapi.interfaces.ITransaction;
import com.wallet.walletapi.interfaces.IWallet;

//@Configuration
//jika tidak di-import  di main maka di tambahkan anotasi di setiap class bean
public class BeanConfig {
	
	@Bean
	public ICustomer iCustomer() {
		return new CustomerImpl();
	}
	
	@Bean
	public IAccount iAccount() {
		return new AccountImpl();
	}
	
	@Bean
	public IWallet iWallet() {
		return new WalletImpl();
	}
	
	@Bean
	public ITransaction iTransaction() {
		return new TransactionImpl();
	}
}

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
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *   @RIFQIABRORY
 *   web developer
 *   enigma batch II
 * */
//@Configuration = if not imported in the main app,than add anotation every beans class
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

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedMethods("HEAD","GET","PUT","POST","DELETE","PATCH");
			}
		};
	}
}

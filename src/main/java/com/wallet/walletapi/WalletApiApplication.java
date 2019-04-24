package com.wallet.walletapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.wallet.walletapi.config.BeanConfig;

@SpringBootApplication
@Import({BeanConfig.class})
@EntityScan({"com.wallet.walletapi.model","com.wallet.walletapi"})
public class WalletApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(WalletApiApplication.class, args);
	}

}

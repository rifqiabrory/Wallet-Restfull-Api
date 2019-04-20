package com.wallet.walletapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "WalletAccount")
@Table(name = "tbl_wallet_account")
public class WalletAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_wallet")
	private int idWallet;

	@ManyToOne
	@JoinColumn(name = "account_number")
	private Account account;

	public int getIdWallet() {
		return idWallet;
	}

	public void setIdWallet(int idWallet) {
		this.idWallet = idWallet;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}

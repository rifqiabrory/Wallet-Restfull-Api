package com.wallet.walletapi.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *   @RIFQIABRORY
 *   web developer
 *   enigma batch II
 * */
@Entity(name = "Wallet")
@Table(name = "tbl_wallet")
public class Wallet {
	@Id
	@Column(name = "id_wallet")
	private int idWallet;

	@Column(name = "description")
	private String description;

	@Column(name = "created_date", insertable = false)
	private Date createdDate;

	@ManyToOne
	@JoinColumn(name="account_number")
	private Account account;

	public int getIdWallet() {
		return idWallet;
	}

	public void setIdWallet(int idWallet) {
		this.idWallet = idWallet;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}

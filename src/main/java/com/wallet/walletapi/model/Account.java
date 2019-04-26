package com.wallet.walletapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

import javax.persistence.*;

/**
 *   @RIFQIABRORY
 *   web developer
 *   enigma batch II
 * */
@Entity(name="Account")
@Table(name="tbl_account")
public class Account {
	@Id
	@Column(name="account_number")
	private int accountNumber;
	
	@Column(name="account_name")
	private String accountName;

	@Column(name="open_date",insertable = false)
	private Date openDate;
	
	@Column(name="balance")
	private double balance;

	@ManyToOne
	@JoinColumn(name="customer_number")
	private Customer customer;

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}

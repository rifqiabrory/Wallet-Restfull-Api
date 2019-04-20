package com.wallet.walletapi.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/* Relasi Database,
 * Entity ini yang Mengirim 
 * ForeignKey ke CustomerEntity(PrimeryKey)
 * dengan ERD @ManyToOne @JoinColumn
*/

@Entity(name="Account")
@Table(name="tbl_account")
public class Account {
	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="account_number")
	private int accountNumber;
	
	@Column(name="account_name")
	private String accountName;
	
	@Column(name="open_date")
	private Date openDate;
	
	@Column(name="balance")
	private double balance;
	
//	@Column(name="customer_number")
//	private int customerNumber;
	
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

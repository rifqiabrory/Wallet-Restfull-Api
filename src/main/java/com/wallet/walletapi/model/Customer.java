package com.wallet.walletapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *   @RIFQIABRORY
 *   web developer
 *   enigma batch II
 * */
@Entity(name = "Customer")
@Table(name = "tbl_customer")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_number")	
	private int customerNumber;

	@Column(name = "firstname")
	private String firstName;

	@Column(name = "lastname")
	private String lastName;
	
	@Column(name = "birthdate")
	private String birthDate;
	
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private  String password;
	

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(int customerNumber) {
		this.customerNumber = customerNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
}

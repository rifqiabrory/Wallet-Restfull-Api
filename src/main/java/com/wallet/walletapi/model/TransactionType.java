package com.wallet.walletapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "TransactionType")
@Table(name = "tbl_transaction_type")
public class TransactionType {

	@Id
//	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "transaction_type")
	private int transactionType;

	@Column(name = "description")
	private String description;

	public int getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(int transactionType) {
		this.transactionType = transactionType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}

package com.wallet.walletapi.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import com.wallet.walletapi.exception.NotFoundException;
import com.wallet.walletapi.interfaces.ITransaction;
import com.wallet.walletapi.model.Account;
import com.wallet.walletapi.model.Transaction;

/**
 *   @RIFQIABRORY
 *   web developer
 *   enigma batch II
 * */
public class TransactionImpl implements ITransaction {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Transaction> getListTransaction() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Transaction> query = builder.createQuery(Transaction.class);
		Root<Transaction> root = query.from(Transaction.class);

		Query q = em.createQuery(query);
		return q.getResultList();
	}

	@Override
	@Transactional
	public Transaction getById(int idTransaction) throws NotFoundException {
		return em.find(Transaction.class, idTransaction);
	}

	@Override
	@Transactional
	public Transaction topUp(Transaction transaction) {
		Transaction trans = em.merge(transaction);
		return trans;
	}

	@Override
	@Transactional
	public Transaction transfer(Transaction transaction) {
		Transaction trans = em.merge(transaction);
		return trans;
	}

	@Override
	@Transactional
	public Transaction widthraw(Transaction transaction) {
		Transaction trans = em.merge(transaction);
		return trans;
	}

	@Override
	public List<Transaction> getListTransaction1(int accountNumber) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Transaction> query = builder.createQuery(Transaction.class);
		Root<Transaction> root = query.from(Transaction.class);

		query.select(root).where(builder.equal(root.get("transaction").get("accountNumber"), accountNumber));
		Query q = em.createQuery(query);
		return q.getResultList();
	}


	
	

}

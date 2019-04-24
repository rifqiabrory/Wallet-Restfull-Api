package com.wallet.walletapi.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.transaction.annotation.Transactional;

import com.wallet.walletapi.exception.NotFoundException;
import com.wallet.walletapi.interfaces.IAccount;
import com.wallet.walletapi.model.Account;
import com.wallet.walletapi.model.Customer;

/**
 *   @RIFQIABRORY
 *   web developer
 *   enigma batch II
 * */
public class AccountImpl implements IAccount {
	
	@PersistenceContext
	private EntityManager em;

	@Transactional
	@Override
	public Account getById(int accountNumber) throws NotFoundException{
		return em.find(Account.class, accountNumber);
	}

	@Transactional
	@Override
	public Account saveAccount(Account account) {
		Account acc =em.merge(account);
		return acc;
	}

	@Transactional
	@Override
	public Account delete(Account account) {
		em.remove(account);
		return account;
	}

	@Transactional
	@Override
	public List<Account> getListAccount(int customerNumber) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Account> query = builder.createQuery(Account.class);
		Root<Account> root = query.from(Account.class);
		
		query.select(root).where(builder.equal(root.get("customer").get("customerNumber"), customerNumber));
		
		Query q = em.createQuery(query);
				
		return q.getResultList();
	}

	@Transactional
	@Override
	public double getBalance(int accountNumber) {
		
		double balance ;
		Account q = em.find(Account.class, accountNumber);
		balance= q.getBalance();
		return  balance;
	}

	@Transactional
	@Override
	public Account updateBalance(int anCredit, double result) {
		Account q = em.find(Account.class, anCredit);
		q.setBalance(result);
		//em.merge(q);
		return em.merge(q);
	}
}
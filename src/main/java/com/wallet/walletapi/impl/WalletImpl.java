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
import com.wallet.walletapi.interfaces.IWallet;
import com.wallet.walletapi.model.Wallet;

/**
 *   @RIFQIABRORY
 *   web developer
 *   enigma batch II
 * */
public class WalletImpl implements IWallet {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<Wallet> getListWallet() {
		 Query query = em.createQuery("From Wallet");
		 return query.getResultList();
	}

	@Transactional
	@Override
	public Wallet save(Wallet wallet) {
		Wallet cst = em.merge(wallet);
		return cst;
	}

	@Transactional
	@Override
	public Wallet update(Wallet wallet) {
		Wallet cust = em.merge(wallet);
		return cust;
	}

	@Transactional
	@Override
	public Wallet delete(Wallet idWallet) {
		em.remove(idWallet);
		return idWallet;
	}

	@Override
	public List<Wallet> getListWallet1(int idWallet) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Wallet> query = builder.createQuery(Wallet.class);
		Root<Wallet> root = query.from(Wallet.class);
		
		query.select(root).where(builder.equal(root.get("account").get("accountNumber"), idWallet));
		
		Query q = em.createQuery(query);
				
		return q.getResultList();
	}

	@Transactional
	@Override
	public Wallet getById(int idWallet) throws NotFoundException {
		return em.find(Wallet.class, idWallet);
	}

}

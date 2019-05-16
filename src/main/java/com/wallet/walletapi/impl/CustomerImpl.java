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
import com.wallet.walletapi.interfaces.ICustomer;
import com.wallet.walletapi.model.Customer;

public class CustomerImpl implements ICustomer {

	@PersistenceContext
	private EntityManager em;


	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	@Override
	public List<Customer> getListCustomer() {
		 Query query = em.createQuery("From Customer");
		 return query.getResultList();
	}

	@Transactional
	@Override
	public Customer getById(int customerNumber) throws NotFoundException{
		return em.find(Customer.class, customerNumber);
	}

	@Transactional
	@Override
	public Customer saveCustomer(Customer customer) {
		Customer cst = em.merge(customer);
		return cst;
	}

	@Transactional
	@Override
	public Customer delete(Customer customer) {
		em.remove(customer);
		return customer;
	}

	@Transactional
	@Override
	public Customer updateCustomer(Customer customer) {
		Customer cust = em.merge(customer);
		return cust;
	}

	@Override
	public Customer getCustomerByUsernamePassword(String username, String password) throws NotFoundException{
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Customer> query = builder.createQuery(Customer.class);
		Root<Customer> root = query.from(Customer.class);

		query.select(root).where(
				builder.and(
						builder.like(root.get("username"), username),
						builder.like(root.get("password"), password)));
		Query qry = em.createQuery(query);
		return (Customer) qry.getSingleResult();
	}

	@Override
	public Customer getCustomerByUsername(String username) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Customer> query = builder.createQuery(Customer.class);
		Root<Customer> root = query.from(Customer.class);

		query.select(root).where( builder.equal(root.get("username"), username) );
		Query qry = em.createQuery(query);
		return (Customer) qry.getSingleResult();
	}


}


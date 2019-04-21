package com.wallet.walletapi.impl;

import java.util.List;
//import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Predicate;
//import javax.persistence.criteria.Root;

import org.springframework.transaction.annotation.Transactional;

//import org.springframework.util.StringUtils;

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
		// usual query
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
		// int number = new Random().nextInt(900000)+1000000;
		Customer cst = em.merge(customer);
		// cst.setCustomerNumber(number);
		return cst;
	}

	@Transactional
	@Override
	public Customer delete(Customer customer) {
//		Customer customer = em.find(Customer.class, customerNumber);
//		em.remove(customer);
//		return customer;
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
	public Customer getByUsernamePassword(String username, String password) {
		// TODO Auto-generated method stub
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Customer> query = builder.createQuery(Customer.class);
		Root<Customer> root = query.from(Customer.class);
		
		//Query query2 = em.createQuery("From Customer");
		query.select(root).where(
				builder.and(
						builder.equal(root.get("username"), username),
						builder.like(root.get("password"), password)));
//		if (query==null) {
//			//throw new EntityNotFoundException("500", "data not found");
///		}else {
//			query.setParameter("name", username);
//			query.setParameter("pass", password);
			Query qry = em.createQuery(query);
			//q.uniqueResult();
//	}
		return (Customer) qry.getSingleResult();
	}
	

}


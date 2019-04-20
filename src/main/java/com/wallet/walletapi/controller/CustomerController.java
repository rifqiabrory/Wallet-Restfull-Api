package com.wallet.walletapi.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wallet.walletapi.dto.CommonResponse;
import com.wallet.walletapi.exception.NotFoundException;
import com.wallet.walletapi.exception.UserException;
import com.wallet.walletapi.interfaces.ICustomer;
import com.wallet.walletapi.model.Customer;

@RestController
@RequestMapping("api")
@CrossOrigin
public class CustomerController {
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);
	
	public static final String URl_REQUEST_CUSTOMER = "customer"; //create
	public static final String URl_REQUEST_CUSTOMERS_LIST = "customers";//read
	public static final String URl_REQUEST_CUSTOMER_BY_ID = "customer/{customer_number}";//Get By Customer Number
	public static final String URl_REQUEST_CUSTOMER_LOGIN = "login/{username}/{password}";
	@Autowired
	private ICustomer ic;

	@CrossOrigin
	@GetMapping(value=URl_REQUEST_CUSTOMERS_LIST,produces=MediaType.APPLICATION_JSON_VALUE)
	public CommonResponse<List<Customer>> getCustomerList() throws NotFoundException {

		List<Customer> customers =  ic.getListCustomer();
        CommonResponse<List<Customer>> respon = new CommonResponse<>();
        if (!customers.isEmpty()) {
            respon.setData(customers);
        } else {
            throw new NotFoundException("444", "Customers doesn't exist!");
        }
        return respon;

	}
	
	@GetMapping(value=URl_REQUEST_CUSTOMER_LOGIN)
	public CommonResponse<Customer> getCustomerByUsernamePassword(@PathVariable String username,@PathVariable String password) throws NotFoundException{
		Customer customer = ic.getByUsernamePassword(username, password);
		CommonResponse<Customer> resp = new CommonResponse<>();
		if (!StringUtils.isEmpty(customer)) {
			resp.getData();
		} else {
			throw new NotFoundException("444", "Customer doesn't exist!");
		}
		resp.setData(ic.getByUsernamePassword(username, password));
		return resp;}
	
	@CrossOrigin
	@PostMapping(value=URl_REQUEST_CUSTOMER)
	public CommonResponse<Customer> createCustomer(@RequestBody Customer customer) {
		CommonResponse<Customer> cust = new CommonResponse<>();
		Customer cst = ic.saveCustomer(customer);
		cust.setData(cst);
		return cust;
	}
	
	@CrossOrigin
	@GetMapping(value=URl_REQUEST_CUSTOMER_BY_ID)
	public CommonResponse<Customer> getCustomerById(@PathVariable (name = "customer_number") int customer_number) throws UserException, NotFoundException {
		
			Customer customer = ic.getById(customer_number);
			CommonResponse<Customer> resp = new CommonResponse<>();
			if (customer==null) {
				LOGGER.error(String.format("Customer ID %d not found", customer_number));
				throw new NotFoundException("444", String.format("Customer ID %d not found", customer_number));
			} else {
				resp.setData(customer);
			}
		return resp;
	}
	
	@CrossOrigin
	@DeleteMapping(value=URl_REQUEST_CUSTOMER_BY_ID)
	public CommonResponse<Customer> deleteCustomer(@PathVariable (name = "customer_number") int customer_number) throws NotFoundException {
		Customer check = ic.getById(customer_number);
		CommonResponse<Customer> resp = new CommonResponse<>();
		if (check==null) {
			throw new NotFoundException("444", String.format("Customer ID %d not found", customer_number));
		} else {
			resp.setData(ic.delete(check));
		}
		return resp;
	}
	
	@CrossOrigin
	@PutMapping(value=URl_REQUEST_CUSTOMER_BY_ID)
	public CommonResponse<Customer> updateCustomerBy(@RequestBody Customer customer) throws NotFoundException {
		Customer check = ic.getById(customer.getCustomerNumber());
		CommonResponse<Customer> resp = new CommonResponse<>();
		if (check==null) {
			resp.setstatus("102");
			resp.setmessage("Not Found");
		} else {
			ic.saveCustomer(customer);
			resp.setData(customer);
		}
		return resp;
	}
}

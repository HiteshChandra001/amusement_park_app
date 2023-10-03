package com.masai.service;

import java.util.List;

import com.masai.entity.Customer;

public interface CustomerService {

	public Customer insertCustomer(Customer customer);

	public Customer updateCustomer(Customer customer);

	public Customer deleteCustomer(Integer customerId);

	public List<Customer> viewCustomers();

	public Customer viewCustomer(Integer customerId);

	public Customer validateCustomer(String username, String password);
	
	public Customer viewCustomerByEmail(String email);

}

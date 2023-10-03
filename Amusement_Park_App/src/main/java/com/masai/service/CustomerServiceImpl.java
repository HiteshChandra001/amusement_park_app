package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.masai.entity.Customer;
import com.masai.exception.NotFoundException;
import com.masai.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	private CustomerRepository customerRepository;

	// constructor injection
	public CustomerServiceImpl(CustomerRepository customerRepository) {
		super();
		this.customerRepository = customerRepository;
	}

	@Override
	public Customer insertCustomer(Customer customer) {
		// validateCustomer(customer.getUsername(), customer.getPassword());
		return customerRepository.save(customer);
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		// validateCustomer(customer.getUsername(), customer.getPassword());
		Optional<Customer> cus = customerRepository.findById(customer.getCustomerId());
		if (cus.isPresent()) {
			//we are here, means customer is available so we can proceed for updation
			return customerRepository.save(customer);
		}
		throw new NotFoundException("customer not found for customer Id " + customer.getCustomerId());
	}

	@Override
	public Customer deleteCustomer(Integer customerId) {
		Optional<Customer> cus = customerRepository.findById(customerId);
		if (cus.isPresent()) {
			// you are here means customer is present
			customerRepository.deleteById(customerId);
			return cus.get();
		}
		throw new NotFoundException("No customer found with customerid " + customerId);
	}

	@Override
	public List<Customer> viewCustomers() {
		List<Customer> customers = customerRepository.findAll();
		if (customers.size() == 0) {
			throw new NotFoundException("No Customer Found");
		}
		return customers;
	}

	@Override
	public Customer viewCustomer(Integer customerId) {
		Optional<Customer> cus = customerRepository.findById(customerId);
		if (cus.isPresent()) {
			return cus.get();
		}
		throw new NotFoundException("No Customer found with customerId " + customerId);
	}

	@Override
	public Customer validateCustomer(String username, String password) {
		return null;
	}

	@Override
	public Customer viewCustomerByEmail(String email) {
		Optional<Customer> cus = customerRepository.findByEmail(email);
		if (cus.isPresent()) {
			//we are here means customer is available with given email id
			return cus.get();
		}
		throw new NotFoundException("No customer found with email " + email);
	}

}

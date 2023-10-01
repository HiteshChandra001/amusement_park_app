package com.masai.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.entity.Activity;
import com.masai.entity.Customer;
import com.masai.entity.Ticket;
import com.masai.service.CustomerService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path = "/customers")
@Slf4j
public class CustomerController {

	private CustomerService customerService;

	//constructor injection
	public CustomerController(CustomerService customerService) {
		super();
		this.customerService = customerService;
	}

	@PostMapping("/customers")
	public ResponseEntity<Customer> insertCustomer(@RequestBody @Valid Customer customer) {
		//before passing customer to the service layer first we need to set the customer for the ticket
		List<Ticket> tickets = customer.getTickets();
		if(tickets!=null) {
			for(Ticket ticket : tickets) {
				// set the customer for each ticket
				ticket.setCustomer(customer);
			}
		}
		Customer cus = customerService.insertCustomer(customer);
		log.info("Customer Added Successfully");
		return new ResponseEntity<Customer>(cus, HttpStatus.CREATED);
	}
	
	@PutMapping("/customers")
	public ResponseEntity<Customer> updateCustomer(@RequestBody @Valid Customer customer){
		Customer updatedCustomer = customerService.updateCustomer(customer);
		log.info("Customer updated successfully");
		return new ResponseEntity<Customer>(updatedCustomer, HttpStatus.OK);
	}
	
	@DeleteMapping("/customers/{customerId}")
	public ResponseEntity<Customer> deleteCustomer(@PathVariable Integer customerId){
		Customer deletedCustomer = customerService.deleteCustomer(customerId);
		log.info("Customer deleted successfully");
		return new ResponseEntity<Customer>(deletedCustomer, HttpStatus.OK);
	}
	
	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> viewCustomers(){
		List<Customer> customers = customerService.viewCustomers();
		return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
	}
}

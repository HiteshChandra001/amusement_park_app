package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.masai.entity.Customer;
import com.masai.entity.Ticket;
import com.masai.exception.InvalidInputException;
import com.masai.repository.CustomerRepository;
import com.masai.repository.TicketRepository;

@Service
public class TicketServiceImpl implements TicketService {

	private CustomerRepository customerRepository;
	private TicketRepository ticketRepository;
	
	
	public TicketServiceImpl(CustomerRepository customerRepository, TicketRepository ticketRepository) {
		super();
		this.customerRepository = customerRepository;
		this.ticketRepository = ticketRepository;
	}

	@Override
	public Ticket insertTicket(Ticket ticket) {
		return ticketRepository.save(ticket);
	}

	@Override
	public Ticket updateTicket(Ticket ticket) {
		Optional<Ticket> find = ticketRepository.findById(ticket.getTicketId());
		if(!find.isPresent())throw new InvalidInputException("ticket id doesnot exist");
		return ticketRepository.save(ticket);
	}

	@Override
	public Ticket deleteTicket(Integer ticketId) {
		Optional<Ticket> find = ticketRepository.findById(ticketId);
		if(!find.isPresent())throw new InvalidInputException("ticket id doesnot exist");
		ticketRepository.deleteById(ticketId);
		return find.get();
	}

	@Override
	public List<Ticket> viewAllTickets(Integer customerId) {
		Optional<Customer> find = customerRepository.findById(customerId);
		if(!find.isPresent())throw new InvalidInputException("customer id doesnot exist");
		return find.get().getTickets();
	}

}

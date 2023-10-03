package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.masai.entity.Customer;
import com.masai.entity.Ticket;
import com.masai.exception.NotFoundException;
import com.masai.repository.TicketRepository;

@Service
public class TicketServiceImpl implements TicketService {

	private TicketRepository ticketRepository;

	// constructor injection
	public TicketServiceImpl(TicketRepository ticketRepository) {
		super();
		this.ticketRepository = ticketRepository;
	}

	@Override
	public Ticket insertTicket(Ticket ticket) {
		return ticketRepository.save(ticket);
	}

	@Override
	public Ticket updateTicket(Ticket ticket) {
		Optional<Ticket> t = ticketRepository.findById(ticket.getTicketId());
		if (t.isPresent()) {
			// we are here means ticket is available for ticket Id;
			return ticketRepository.save(ticket);
		}
		throw new NotFoundException("No ticket is available for ticket Id " + ticket.getTicketId());
	}

	@Override
	public Ticket deleteTicket(Integer ticketId) {
		Optional<Ticket> t = ticketRepository.findById(ticketId);
		if (t.isPresent()) {
			ticketRepository.deleteById(ticketId);
			return t.get();
		}
		throw new NotFoundException("No ticket is available for ticket Id " + ticketId);
	}

	@Override
	public List<Ticket> viewAllTickets(Integer customerId) {
		if (ticketRepository.count() == 0) {
			throw new NotFoundException("No ticket found");
		}
		List<Ticket> tickets = ticketRepository.findAll();
		return tickets;
	}

}

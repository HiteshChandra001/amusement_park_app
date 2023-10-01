package com.masai.service;

import java.util.List;

import com.masai.entity.Ticket;

public interface TicketService {

	public Ticket insertTicket(Ticket ticket);
	public Ticket updateTicket(Ticket ticket);
	public Ticket deleteTicket(Integer ticketId);
	public List<Ticket> viewAllTickets(Integer customerId);
	
}

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

import com.masai.entity.Ticket;
import com.masai.service.TicketService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path = "/tickets")
@Slf4j
public class TicketController {

	private TicketService ticketService;

	// constructor injection
	public TicketController(TicketService ticketService) {
		super();
		this.ticketService = ticketService;
	}

	@GetMapping("/tickets/{customerId}")
	public ResponseEntity<List<Ticket>> viewAllTickets(@PathVariable Integer customerId) {
		List<Ticket> tickets = ticketService.viewAllTickets(customerId);
		return new ResponseEntity<List<Ticket>>(tickets, HttpStatus.OK);
	}

	@PostMapping("/tickets")
	public ResponseEntity<Ticket> insertTicket(@RequestBody Ticket ticket) {
		Ticket t = ticketService.insertTicket(ticket);
		log.info("Ticket added successfully");
		return new ResponseEntity<Ticket>(t, HttpStatus.CREATED);
	}

	@PutMapping("/tickets")
	public ResponseEntity<Ticket> updateTicket(@RequestBody Ticket ticket) {
		Ticket t = ticketService.updateTicket(ticket);
		log.info("Ticket updated successfully");
		return new ResponseEntity<Ticket>(t, HttpStatus.OK);
	}

	@DeleteMapping("/tickets/{ticketId}")
	public ResponseEntity<Ticket> deleteTicket(@RequestBody Integer ticketId) {
		Ticket t = ticketService.deleteTicket(ticketId);
		log.info("Ticket deleted successfully");
		return new ResponseEntity<Ticket>(t, HttpStatus.OK);
	}
}

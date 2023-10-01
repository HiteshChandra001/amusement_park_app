package com.masai.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.entity.Customer;
import com.masai.entity.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
	List<Ticket> findByCustomer(Customer customer);
	
	List<Ticket> findByOrderByCustomerUsername();
	
	List<Ticket> findByOrderByDateTime();
	
	List<Ticket> findByDateTimeGreaterThanEqualsAndLesserThanEquals(LocalDateTime fromDate,LocalDateTime toDate);
}

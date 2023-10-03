package com.masai.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	//SELECT c FROM customer c WHERE c.email = ?
	Optional<Customer> findByEmail(String email);
}

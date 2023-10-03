package com.masai.repository;

import java.util.List;
<<<<<<< HEAD
import java.util.Optional;
=======
>>>>>>> main

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.masai.entity.Activity;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Integer> {

<<<<<<< HEAD
	List<Activity> findByChargesLessThan(float charges);
	

=======
	List<Activity> findByCharges(float charge);
>>>>>>> main
}

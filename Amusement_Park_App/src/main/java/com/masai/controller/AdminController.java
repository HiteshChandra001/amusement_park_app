package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.masai.entity.Admin;
import com.masai.service.AdminService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path = "/admins")
@Slf4j
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@PostMapping("/admin")
	public ResponseEntity<Admin> insertAdmin(@Valid @RequestBody Admin admin){
		Admin ad = adminService.insertAdmin(admin);
		log.info("admin added successfully");
		return new ResponseEntity<Admin>(ad,HttpStatus.CREATED);
	}
	
	@PutMapping("/admin")
	public ResponseEntity<Admin> updateAdmin(@Valid @RequestBody Admin admin){
		Admin ad = adminService.updateAdmin(admin);
		log.info("admin updated successfully");
		return new ResponseEntity<Admin>(ad,HttpStatus.OK);
	}
	
	@DeleteMapping("/admin/{id}")
	public ResponseEntity<Admin> deleteAdmin(@PathVariable Integer id){
		Admin ad = adminService.deleteAdmin(id);
		log.info("admin deleted successfully");
		return new ResponseEntity<Admin>(ad,HttpStatus.OK);
	}
	
	@GetMapping("/activity/{id}")
	public ResponseEntity<List<Activity>> getAllActivities(@PathVariable Integer id){
		List<Activity> list = adminService.getAllActivities(id);
		return new ResponseEntity<List<Activity>>(list,HttpStatus.OK);
	}
	
	@GetMapping("/activity")
	public ResponseEntity<List<Activity>> getAllActivities(){
		List<Activity> list = adminService.getAllActivities();
		return new ResponseEntity<List<Activity>>(list,HttpStatus.OK);
	}
	
	
}

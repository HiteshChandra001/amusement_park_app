package com.masai.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.masai.entity.Activity;
import com.masai.entity.Admin;
import com.masai.entity.Customer;
import com.masai.entity.Ticket;
import com.masai.exception.InvalidInputException;
import com.masai.exception.NotFoundException;
import com.masai.repository.ActivityRepository;
import com.masai.repository.AdminRepository;
import com.masai.repository.CustomerRepository;

@Service
public class AdminServiceImpl implements AdminService {

	private AdminRepository adminRepository;
	
	private CustomerRepository customerRepository;
	
	private ActivityRepository activityRepository;
	
	
	
	//constructor injection
	public AdminServiceImpl(AdminRepository adminRepository, CustomerRepository customerRepository, ActivityRepository activityRepository) {
		super();
		this.adminRepository = adminRepository;
		this.customerRepository = customerRepository;
		this.activityRepository = activityRepository;
	}

	@Override
	public Admin insertAdmin(Admin admin) {
		return adminRepository.save(admin);
	}

	@Override
	public Admin updateAdmin(Admin admin) {
		Optional<Admin> a = adminRepository.findById(admin.getAdminId());
		if(a.isPresent()) {
			return adminRepository.save(admin);
		}
		throw new NotFoundException("Admin not found with admin id "+ admin.getAdminId());
	}

	@Override
	public Admin deleteAdmin(Integer adminId) {
		Optional<Admin> find = adminRepository.findById(adminId);
		if(!find.isPresent())throw new InvalidInputException("admin id doesnot exist");
		 adminRepository.deleteById(adminId);
		 return find.get();
		 
	}

	@Override
	public List<Activity> getAllActivities(Integer customerId) {
		Optional<Customer> c = customerRepository.findById(customerId);
		if(c.isPresent()) {
			List<Ticket> tickets = c.get().getTickets();
			if(!tickets.isEmpty()) {
				List<Activity> activities = new ArrayList<>();
				for(Ticket t : tickets) {
					activities.add(t.getActivity());
				}
				return activities;
			}
			throw new NotFoundException("No activity found for customer id " + customerId);
		}
		throw new NotFoundException("No customer found for customer Id " + customerId);
	}

	@Override
	public List<Activity> getAllActivities() {
		List<Activity> activities = activityRepository.findAll();
		if(!activities.isEmpty()) {
			return activities;
		}
		throw new NotFoundException("No activity found");
	}

//	@Override
//	public List<Activity> getActivitiesCustomerwise() {
//		List<Customer> customers = customerRepository.findAll();
//		if(customers.size()==0) {
//			throw new NotFoundException("NO customer found");
//		}
//		Map<Customer, List<Activity>> map = new HashMap<>();
//		for(Customer c : customers) {
//			Integer customerId = c.getCustomerId();
//			
//		}
//	}

//	@Override
//	public List<Activity> getActivitiesDatewise() {
//		List<Ticket> list = ticketRepository.findByOrderByDateTime();
//		List<Activity> actList = list.stream().map((t->t.getActivity())).toList();
//		
//		if(actList.size()==0)throw new NotFoundException("no activity found");
//		return actList;
//		return null;
//	}

//	@Override
//	public List<Activity> getAllActivitiesForDays(Integer customerId, LocalDateTime fromDate, LocalDateTime toDate) {
//		Optional<Customer> find = customerRepository.findById(customerId);
//		if(!find.isPresent())throw new InvalidInputException("customer id doesnot exist");
//		
//		List<Ticket> list = ticketRepository.findByDateTimeGreaterThanEqualsAndLesserThanEquals(fromDate, toDate);
//
//		List<Activity> actList = list.stream().map((t->t.getActivity())).toList();
//		
//		if(actList.size()==0)throw new NotFoundException("no activity found");
//		return actList;
		
//		return null;
//	}
//
//	@Override
//	public List<Activity> getActivitiesCustomerwise() {
//		// TODO Auto-generated method stub
//		return null;
//	}

}

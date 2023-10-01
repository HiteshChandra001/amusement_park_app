package com.masai.service;

import java.time.LocalDateTime;
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
import com.masai.repository.TicketRepository;

@Service
public class AdminServiceImpl implements AdminService {

	private AdminRepository adminRepository;
	private ActivityRepository activityRepository;
	private CustomerRepository customerRepository;
	private TicketRepository ticketRepository;
	
	
	public AdminServiceImpl(AdminRepository adminRepository, ActivityRepository activityRepository,
			CustomerRepository customerRepository, TicketRepository ticketRepository) {
		super();
		this.adminRepository = adminRepository;
		this.activityRepository = activityRepository;
		this.customerRepository = customerRepository;
		this.ticketRepository = ticketRepository;
	}

	@Override
	public Admin insertAdmin(Admin admin) {
		return adminRepository.save(admin);
	}

	@Override
	public Admin updateAdmin(Admin admin) {
		Optional<Admin> find = adminRepository.findById(admin.getAdminId());
		if(!find.isPresent())throw new InvalidInputException("admin id doesnot exist");
		return adminRepository.save(admin);
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
		Optional<Customer> find = customerRepository.findById(customerId);
		if(!find.isPresent())throw new InvalidInputException("customer id doesnot exist");
		
		List<Ticket> list = ticketRepository.findByCustomer(find.get());
		List<Activity> actList = list.stream().map((t->t.getActivity())).toList();
		
		if(actList.size()==0)throw new NotFoundException("no activity found");
		return actList;
		
	}

	@Override
	public List<Activity> getAllActivities() {
		 List<Activity> list = activityRepository.findAll();
		 if(list.size()==0)throw new NotFoundException("no activity found");
		 return list;
	}

	@Override
	public List<Activity> getActivitiesCustomerwise() {
		List<Ticket> list = ticketRepository.findByOrderByCustomerUsername();
		List<Activity> actList = list.stream().map((t->t.getActivity())).toList();
		
		if(actList.size()==0)throw new NotFoundException("no activity found");
		return actList;
	}

	@Override
	public List<Activity> getActivitiesDatewise() {
		List<Ticket> list = ticketRepository.findByOrderByDateTime();
		List<Activity> actList = list.stream().map((t->t.getActivity())).toList();
		
		if(actList.size()==0)throw new NotFoundException("no activity found");
		return actList;
	}

	@Override
	public List<Activity> getAllActivitiesForDays(Integer customerId, LocalDateTime fromDate, LocalDateTime toDate) {
		Optional<Customer> find = customerRepository.findById(customerId);
		if(!find.isPresent())throw new InvalidInputException("customer id doesnot exist");
		
		List<Ticket> list = ticketRepository.findByDateTimeGreaterThanEqualsAndLesserThanEquals(fromDate, toDate);

		List<Activity> actList = list.stream().map((t->t.getActivity())).toList();
		
		if(actList.size()==0)throw new NotFoundException("no activity found");
		return actList;
		
	}

}

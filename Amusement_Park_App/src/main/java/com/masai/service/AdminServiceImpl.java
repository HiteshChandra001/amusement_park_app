package com.masai.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.masai.entity.Activity;
import com.masai.entity.Admin;

@Service
public class AdminServiceImpl implements AdminService {

	@Override
	public Admin insertAdmin(Admin admin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Admin updateAdmin(Admin admin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Admin deleteAdmin(Integer adminId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Activity> getAllActivities(Integer customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Activity> getAllActivities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Activity> getActivitiesCustomerwise() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Activity> getActivitiesDatewise() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Activity> getAllActivitiesForDays(Integer customerId, LocalDateTime fromDate, LocalDateTime toDate) {
		// TODO Auto-generated method stub
		return null;
	}

}

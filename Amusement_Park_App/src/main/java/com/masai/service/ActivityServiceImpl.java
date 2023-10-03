package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.masai.entity.Activity;
import com.masai.exception.NotFoundException;
import com.masai.repository.ActivityRepository;

@Service
public class ActivityServiceImpl implements ActivityService {

	private ActivityRepository activityRepository;
	
	// constructor injection
	public ActivityServiceImpl(ActivityRepository activityRepository) {
		super();
		this.activityRepository = activityRepository;
	}

	@Override
	public Activity insertActivity(Activity activity) {
		return activityRepository.save(activity);
	}

	@Override
	public Activity updateActivity(Activity activity) {
		Optional<Activity> a = activityRepository.findById(activity.getActivityId());
		if (a.isPresent()) {
			// we are here means activity is available
			return a.get();
		}
		throw new NotFoundException("Activity not available for activity Id " + activity.getActivityId());

	}

	@Override
	public Activity deleteActivity(Integer activityId) {
		Optional<Activity> a = activityRepository.findById(activityId);
		if (a.isPresent()) {
			activityRepository.deleteById(activityId);
			return a.get();
		}
		throw new NotFoundException("Activity not availabel for activity Id " + activityId);
	}

	@Override
	public List<Activity> viewActivitiesOfCharges(Float charges) {
		List<Activity> activities = activityRepository.findByChargesLessThan(charges);
		if (activities.size() == 0) {
			throw new NotFoundException("No activity found under this price");
		}
		return activities;
	}

	@Override
	public Integer countActivitiesOfCharges(Float charges) {
		List<Activity> activities = activityRepository.findByChargesLessThan(charges);
		if (activities.size() == 0) {
			throw new NotFoundException("No activity found under this price");
		}
		return (int) activities.stream().count();
	}

}

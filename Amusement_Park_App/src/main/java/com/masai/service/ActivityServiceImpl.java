package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.masai.entity.Activity;
import com.masai.exception.InvalidInputException;
import com.masai.exception.NotFoundException;
import com.masai.repository.ActivityRepository;

@Service
public class ActivityServiceImpl implements ActivityService {

	private ActivityRepository activityRepository;
	
	@Override
	public Activity insertActivity(Activity activity) {
		return activityRepository.save(activity);
	}

	@Override
	public Activity updateActivity(Activity activity) {
		Optional<Activity> find = activityRepository.findById(activity.getActivityId());
		if(!find.isPresent())throw new InvalidInputException("activity id doesnot exist");
		return activityRepository.save(activity);
	}

	@Override
	public Activity deleteActivity(Integer activityId) {
		Optional<Activity> find = activityRepository.findById(activityId);
		if(!find.isPresent())throw new InvalidInputException("activity id doesnot exist");
		 activityRepository.deleteById(activityId);
		 return find.get();
	}

	@Override
	public List<Activity> viewActivitiesOfCharges(Float charges) {
		List<Activity> list = activityRepository.findByCharges(charges);
		if(list.size()==0)throw new NotFoundException("no activity found");
		return list;
	}

	@Override
	public Integer countActivitiesOfCharges(Float charges) {
		List<Activity> list = activityRepository.findByCharges(charges);
		return list.size();
	}

}

package com.masai.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.entity.Activity;
import com.masai.service.ActivityService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path = "/activities")
@Slf4j
public class ActivityController {

	private ActivityService activityService;

	// constructor injection
	public ActivityController(ActivityService activityService) {
		super();
		this.activityService = activityService;
	}

	@PostMapping("/activities")
	public ResponseEntity<Activity> insertActivitiy(@RequestBody Activity activity) {
		Activity ac = activityService.insertActivity(activity);
		log.info("Activity added successfully");
		return new ResponseEntity<Activity>(ac, HttpStatus.CREATED);
	}

	@PutMapping("/activities")
	public ResponseEntity<Activity> updateActivitiy(@RequestBody Activity activity) {
		Activity ac = activityService.updateActivity(activity);
		log.info("Activity updated successfully");
		return new ResponseEntity<Activity>(ac, HttpStatus.OK);
	}

	@DeleteMapping("/activities/{activityId}")
	public ResponseEntity<Activity> deleteActivitiy(@RequestBody Integer activityId) {
		Activity ac = activityService.deleteActivity(activityId);
		log.info("Activity deleted successfully");
		return new ResponseEntity<Activity>(ac, HttpStatus.OK);
	}
}

package com.ud.ag.todo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ud.ag.todo.entity.BalanceTestResult;
import com.ud.ag.todo.service.TaskService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * REST controller for managing Task related functionality
 */
@RestController
@RequestMapping(path = "/api/v1/tasks")
@Api(value = "Task API Resources")
public class TaskController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TaskController.class);
	
	private final TaskService taskService;
	
	@Autowired
	public TaskController(final TaskService taskService) {
		this.taskService = taskService;
	}
	
	/**
	 * Checks if brackets in a string are balanced.
	 * 
	 * @param input String. 
	 * 		lenth should be less then 100
	 * @return {@link BalanceTestResult}
	 */
	@GetMapping(value = "/validateBrackets", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Validation Error") })
	public ResponseEntity<BalanceTestResult> checkBracketsBalanced(@RequestParam("input") final String input) {
		LOGGER.info("checkBracketsBalanced(). Input : {}", input);
		
		if(input.length() > 100) {
			LOGGER.info("Input length > 100. Invalid request.");
			return ResponseEntity.badRequest().body(null);
		}
		
		return ResponseEntity.ok(taskService.checkBracketsBalanced(input));
		
	}
}

package com.ud.ag.todo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ud.ag.todo.entity.TodoItem;
import com.ud.ag.todo.service.TodoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * REST controller for managing Todo related functionality
 */
@RestController
@RequestMapping(path = "/api/v1/todos")
@Api(value = "Todo API Resources")
public class TodoController {

	private static final Logger LOGGER = LoggerFactory.getLogger(TodoController.class);
	
	private final TodoService todoService;
	
	@Autowired
	public TodoController(final TodoService todoService) {
		this.todoService = todoService;
	}

	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Todo Item Successfully retriewed"),
			@ApiResponse(code = 400, message = "Validation Error"),
			@ApiResponse(code = 404, message = "Todo Item Not Found") })
	public ResponseEntity<TodoItem> getTodoItemById(@PathVariable("id") final int id) {
		LOGGER.info("getTodoItemById(). ID : {}", id);

		return new ResponseEntity<>(todoService.getTodoItemById(id), HttpStatus.OK);

	}

}

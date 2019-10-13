package com.ud.ag.todo.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

	
	/**
	 * get a TodoItem for the passed id.
	 * 
	 * @param id TodoItem id
	 * 
	 * @return {@link ResponseEntity<TodoItem>}
	 */
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Todo Item Successfully Retriewed"),
			@ApiResponse(code = 400, message = "Validation Error"),
			@ApiResponse(code = 404, message = "Todo Item Not Found") })
	public ResponseEntity<TodoItem> getTodoItemById(@PathVariable("id") final int id) {
		LOGGER.info("getTodoItemById(). ID : {}", id);
		
		return Optional.ofNullable(todoService.getTodoItemById(id))
				.map(result -> ResponseEntity.ok(result))
        		.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
		
	}
	
	
	/**
	 * create a TodoItem.
	 * 
	 * @param todoItem {@link TodoItem}
	 * 
	 * @return newly created {@link TodoItem}
	 */
	@PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Todo Item Successfully Created"),
			@ApiResponse(code = 400, message = "Validation Error") })
	public ResponseEntity<TodoItem> createTodoItem(@Validated @RequestBody(required = true) TodoItem todoItem) {
		LOGGER.info("createTodoItem(). TodoItem : {} ", todoItem);
		
		return Optional.ofNullable(todoService.createTodoItem(todoItem))
				.map(result -> ResponseEntity.ok(result))
        		.orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
	}
	
	
	/**
	 * update a TodoItem for the passed id.
	 * 
	 * @param id TodoItem id
	 * @param todoItem {@link TodoItem}
	 * 
	 * @return updated {@link TodoItem}
	 */
	@PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Todo Item Successfully Updated"),
			@ApiResponse(code = 400, message = "Validation Error"),
			@ApiResponse(code = 404, message = "Todo Item Not Found") })
	public ResponseEntity<TodoItem> updateTodoItem(@PathVariable("id") final int id,
			@Validated @RequestBody(required = true) TodoItem todoItem) {
		LOGGER.info("updateTodoItem(). ID : {}, TodoItem : {} ", id, todoItem);

		return Optional.ofNullable(todoService.updateTodoItem(id, todoItem))
				.map(result -> ResponseEntity.ok(result))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

}

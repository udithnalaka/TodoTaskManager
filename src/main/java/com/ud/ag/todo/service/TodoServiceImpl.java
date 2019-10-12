package com.ud.ag.todo.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ud.ag.todo.dao.TodoDAO;
import com.ud.ag.todo.entity.TodoItem;

@Service
public class TodoServiceImpl implements TodoService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TodoServiceImpl.class);
	
	private TodoDAO todoDao;
	
	@Autowired
	public TodoServiceImpl(final TodoDAO todoDao) {
		this.todoDao = todoDao;
	}

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public TodoItem getTodoItemById(int id) {
		LOGGER.info("getTodoItemById(). ID : {}", id);
		
		Optional<TodoItem> todo = todoDao.getTodoItemById(id);
		
		//check if invalid id is passed
		if(!todo.isPresent()) {
			return null;
		}
		
		return todo.get();
	}

}

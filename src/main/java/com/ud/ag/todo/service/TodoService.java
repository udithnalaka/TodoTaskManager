package com.ud.ag.todo.service;

import com.ud.ag.todo.entity.TodoItem;

public interface TodoService {

	/**
	 * View a TodoItem for the passed id {@link TodoItem}.
	 *
	 * @param id TodoItem id
	 * 
	 * @return {@link TodoItem}
	 */
	public TodoItem getTodoItemById(final int id);

	
	/**
	 * create a {@link TodoItem}.
	 * 
	 * @param todoItem TodoItem
	 * 
	 * @return newly created {@link TodoItem}.
	 */
	public TodoItem createTodoItem(final TodoItem todoItem);

}

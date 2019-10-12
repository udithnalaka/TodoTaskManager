package com.ud.ag.todo.service;

import com.ud.ag.todo.entity.TodoItem;

public interface TodoService {

	/**
	 * View a Todo Item {@link TodoItem}.
	 *
	 * @param resId result id
	 * @return {@link TodoItem}
	 */
	public TodoItem getTodoItemById(final int id);

}

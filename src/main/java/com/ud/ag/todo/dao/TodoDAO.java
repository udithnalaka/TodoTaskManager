package com.ud.ag.todo.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ud.ag.todo.entity.TodoItem;

/**
 * This class is used to hold {@link TodoItem} data, without using a persistence
 * layer.
 * 
 * @author udith
 *
 */
@Component
public class TodoDAO {

	//Map to hold the Todo items in memory.
	private static Map<Integer, TodoItem> todoItems = new HashMap<>();

	static {
		todoItems.put(1, new TodoItem(1, "Do some exercises", false, "2019-10-13T01:50:58.735Z"));
		todoItems.put(2, new TodoItem(2, "Study ReactJS ", true, "2019-10-01T01:50:58.735Z"));
		todoItems.put(3, new TodoItem(3, "Go to the Dentist", false, "2019-12-13T01:50:58.735Z"));
		todoItems.put(4, new TodoItem(4, "Find an IT job in Brisbane", false, "2019-09-20T01:50:58.735Z"));

	}

	/**
	 * get an item from the TodoItem map.
	 * 
	 * @param id
	 * @return TodoItem
	 */
	public TodoItem getTodoItemById(final int id) {
		
		return todoItems.get(id);
	}

	/**
	 * add a item to the TodoItem map.
	 * 
	 * @param todoItem
	 * @return created TodoItem
	 */
	public TodoItem saveTodoItem(TodoItem todoItem) {

		todoItems.put(todoItem.getId(), todoItem);

		return todoItem;
	}

	/**
	 * update an item in the TodoItem map.
	 * 
	 * @param id
	 * @param todoItem
	 * @return updated TodoItem
	 */
	public TodoItem updateTodoItem(int id, TodoItem todoItem) {

		if (todoItems.containsKey(id)) {
			todoItems.put(id, todoItem);
			return todoItems.get(id);
		}

		return null;
	}

}

package com.ud.ag.todo.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

	private static List<TodoItem> todoItems = new ArrayList<>();

	static {
		todoItems.add(new TodoItem(1, "Do some exercises", false, ""));
		todoItems.add(new TodoItem(2, "Study ReactJS ", true, ""));
		todoItems.add(new TodoItem(3, "Go to the Dentist", false, ""));
		todoItems.add(new TodoItem(4, "Find an IT job in Brisbane", false, ""));

	}

	/**
	 * Check if the passed Todo id is available in the List<TodoItem>, and return the
	 * corresponding TodoItem.
	 * 
	 * @param id
	 * 
	 * @return Optional<TodoItem>
	 */
	public Optional<TodoItem> getTodoItemById(final int id) {
		return todoItems.stream().filter(todo -> todo.getId() == id).findFirst();
	}

	public TodoItem saveTodoItem(TodoItem todoItem) {
		
		if(todoItems.add(todoItem)) {
			return todoItem;
		}
		return null;
	}

}

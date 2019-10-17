package com.ud.ag.todo.unit;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.ud.ag.todo.dao.TodoDAO;
import com.ud.ag.todo.entity.TodoItem;
import com.ud.ag.todo.service.TodoServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class TodoServiceTest {
	
	@Mock
	TodoDAO todoDaoMock;
	
	@InjectMocks
	TodoServiceImpl todoService;
	
	TodoItem todoItem;

	@Before
	public void setUp() throws Exception {
		todoItem = new TodoItem(1, "Mocked", false, "");
	}

	@Test
	public void getTodoItemByIdWithValidIdShouldReturnTodoItem() {
		when(todoDaoMock.getTodoItemById(Mockito.anyInt()))
			.thenReturn(todoItem);
		
		TodoItem todoItem = todoService.getTodoItemById(1);
		
		assertEquals(todoItem, todoItem);
		assertEquals(todoItem.getText(), todoItem.getText());
	}
	
	@Test
	public void getTodoItemByIdWithNonExsistingIdShouldReturnNull() {
		when(todoDaoMock.getTodoItemById(Mockito.anyInt()))
			.thenReturn(null);
		
		assertEquals(null, todoService.getTodoItemById(10));
	}

}

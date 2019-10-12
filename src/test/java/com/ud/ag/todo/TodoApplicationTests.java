package com.ud.ag.todo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.ud.ag.todo.controller.TodoController;
import com.ud.ag.todo.service.TodoService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TodoTaskManagerApplication.class)
@WebAppConfiguration
public class TodoApplicationTests {

	private MockMvc todoMockMvc;

	@Autowired
	private TodoService todoService;

	private static final String TODO_API_PATH = "/api/v1/todos";
	
	private static final int TODO_ID_VALID = 4;
	private static final int TODO_ID_INVALID = 99;
	private static final String TODO_TEXT = "Find an IT job in Brisbane";
	private static final boolean TODO_COMPLETED = false;
	
	private static final String RESPONSE_PARAM_TODOITEM_ID = "id";
	private static final String RESPONSE_PARAM_TODOITEM_TEXT = "text";
	private static final String RESPONSE_PARAM_TODOITEM_COMPLETED = "completed";

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		TodoController todoResource = new TodoController(todoService);

		this.todoMockMvc = MockMvcBuilders.standaloneSetup(todoResource).build();
	}

	@Test
	public void getTodoItemByIdWithValidIdShouldReturnTodoItem() throws Exception {

		todoMockMvc.perform(get(TODO_API_PATH + "/" + TODO_ID_VALID))
			.andExpect(status().isOk())
			.andExpect(jsonPath(RESPONSE_PARAM_TODOITEM_ID).value(TODO_ID_VALID))
			.andExpect(jsonPath(RESPONSE_PARAM_TODOITEM_TEXT).value(TODO_TEXT))
			.andExpect(jsonPath(RESPONSE_PARAM_TODOITEM_COMPLETED).value(TODO_COMPLETED));
	}
	
	
	@Test
	public void getTodoItemByIdWithInValidIdShouldReturnNotFound() throws Exception {

		todoMockMvc.perform(get(TODO_API_PATH + "/" + TODO_ID_INVALID))
			.andExpect(status().isNotFound());
	}

}

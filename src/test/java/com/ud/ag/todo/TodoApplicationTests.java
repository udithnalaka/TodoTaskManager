package com.ud.ag.todo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
	private static final int TODO_ID_VALID = 1;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		TodoController todoResource = new TodoController(todoService);

		this.todoMockMvc = MockMvcBuilders.standaloneSetup(todoResource).build();
	}

	@Test
	public void getTodoItemByIdWithValidIdShouldReturnTodoItem() throws Exception {

		todoMockMvc.perform(get(TODO_API_PATH + "/" + TODO_ID_VALID))
			.andExpect(status().isOk());
	}

}

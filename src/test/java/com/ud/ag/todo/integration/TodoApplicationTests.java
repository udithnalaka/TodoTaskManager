package com.ud.ag.todo.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.apache.logging.log4j.util.Strings;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.ud.ag.todo.TodoTaskManagerApplication;
import com.ud.ag.todo.controller.TodoController;
import com.ud.ag.todo.entity.TodoItem;
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
	
	private static final int TODO_ID_VALID_ID_10 = 10;
	private static final String TODO_TEXT_ID_10 = "Run test case for create todo item";
	private static final String TODO_TEXT_ID_10_INVALID = "Run test case for create todo item with too large text";
	private static final boolean TODO_COMPLETED_ID_10 = false;
	private static final String TODO_CREATEDAT_ID_10 = "12/10/2019";
	
	private static final int TODO_UPDATE_ID_VALID_3 = 3;
	private static final String TODO_UPDATE_TEXT_ID_3 = "Go to the Dentist at greenslopes";
	private static final String TODO_TEXT_ID_3_INVALID = "Run test case for create todo item with too large text";
	private static final String TODO_UPDATE_CREATEDAT_ID_3 = "12/31/2020";
	private static final boolean TODO_UPDATE_COMPLETED_ID_3 = false;
	
	private static final String RESPONSE_PARAM_TODOITEM_ID = "id";
	private static final String RESPONSE_PARAM_TODOITEM_TEXT = "text";
	private static final String RESPONSE_PARAM_TODOITEM_COMPLETED = "completed";
	private static final String RESPONSE_PARAM_TODOITEM_CREATEDAT = "createdAt";
	
	private TodoItem createTodoItemForTesting(int todoId, String text, boolean isCompleted, String createdAt) {
		return new TodoItem(todoId, text, isCompleted, createdAt);
	}


	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		TodoController todoResource = new TodoController(todoService);

		this.todoMockMvc = MockMvcBuilders.standaloneSetup(todoResource).build();
	}

	//// START - test cases for getTodoItemById()
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
	////END - test cases for getTodoItemById()
	
	
	////START - test cases for createTodoItem()
	@Test
	public void createTodoItemWithValidEntityShouldReturnTheNewTodoItem() throws Exception {

		todoMockMvc.perform(post(TODO_API_PATH)
			.contentType(MediaType.APPLICATION_JSON_UTF8)
			.content(TestUtil.convertObjectToJsonBytes(createTodoItemForTesting(
					TODO_ID_VALID_ID_10, TODO_TEXT_ID_10, TODO_COMPLETED_ID_10, TODO_CREATEDAT_ID_10))))
			.andExpect(status().isOk())
			.andExpect(jsonPath(RESPONSE_PARAM_TODOITEM_ID).value(TODO_ID_VALID_ID_10))
			.andExpect(jsonPath(RESPONSE_PARAM_TODOITEM_TEXT).value(TODO_TEXT_ID_10))
			.andExpect(jsonPath(RESPONSE_PARAM_TODOITEM_COMPLETED).value(TODO_COMPLETED_ID_10))
			.andExpect(jsonPath(RESPONSE_PARAM_TODOITEM_CREATEDAT).value(TODO_CREATEDAT_ID_10));
	}
	
	@Test
	public void createTodoItemWithTextSizeGreaterThanFiftyShouldReturnValidationError() throws Exception {

		todoMockMvc.perform(post(TODO_API_PATH)
			.contentType(MediaType.APPLICATION_JSON_UTF8)
			.content(TestUtil.convertObjectToJsonBytes(createTodoItemForTesting(
					TODO_ID_VALID_ID_10, TODO_TEXT_ID_10_INVALID, TODO_COMPLETED_ID_10, TODO_CREATEDAT_ID_10))))
			.andExpect(status().isBadRequest());
		
	}
	
	@Test
	public void createTodoItemWithEmptyTextShouldReturnValidationError() throws Exception {

		todoMockMvc.perform(post(TODO_API_PATH)
			.contentType(MediaType.APPLICATION_JSON_UTF8)
			.content(TestUtil.convertObjectToJsonBytes(createTodoItemForTesting(
					TODO_ID_VALID_ID_10, Strings.EMPTY, TODO_COMPLETED_ID_10, TODO_CREATEDAT_ID_10))))
			.andExpect(status().isBadRequest());
		
	}
	////END - test cases for createTodoItem()
	
	
	////START - test cases for updateTodoItem()
	@Test
	public void updateTodoItemWithValidEntityShouldReturnTheUpdatedTodoItem() throws Exception {

		todoMockMvc
				.perform(patch(TODO_API_PATH + "/" + TODO_UPDATE_ID_VALID_3)
						.contentType(MediaType.APPLICATION_JSON_UTF8)
						.content(TestUtil.convertObjectToJsonBytes(createTodoItemForTesting(TODO_UPDATE_ID_VALID_3,
								TODO_UPDATE_TEXT_ID_3, TODO_UPDATE_COMPLETED_ID_3, TODO_UPDATE_CREATEDAT_ID_3))))
				.andExpect(status().isOk())
				.andExpect(jsonPath(RESPONSE_PARAM_TODOITEM_ID).value(TODO_UPDATE_ID_VALID_3))
				.andExpect(jsonPath(RESPONSE_PARAM_TODOITEM_TEXT).value(TODO_UPDATE_TEXT_ID_3))
				.andExpect(jsonPath(RESPONSE_PARAM_TODOITEM_COMPLETED).value(TODO_UPDATE_COMPLETED_ID_3))
				.andExpect(jsonPath(RESPONSE_PARAM_TODOITEM_CREATEDAT).value(TODO_UPDATE_CREATEDAT_ID_3));
	}
	
	@Test
	public void updateTodoItemWithInvalidIdShouldReturnNotFound() throws Exception {

		todoMockMvc
				.perform(patch(TODO_API_PATH + "/" + TODO_ID_INVALID)
						.contentType(MediaType.APPLICATION_JSON_UTF8)
						.content(TestUtil.convertObjectToJsonBytes(createTodoItemForTesting(TODO_ID_INVALID,
								TODO_UPDATE_TEXT_ID_3, TODO_UPDATE_COMPLETED_ID_3, TODO_UPDATE_CREATEDAT_ID_3))))
				.andExpect(status().isNotFound());
	}
	
	
	@Test
	public void updateTodoItemWithTooLargeTextShouldReturnValidationError() throws Exception {

		todoMockMvc
				.perform(patch(TODO_API_PATH + "/" + TODO_ID_INVALID)
						.contentType(MediaType.APPLICATION_JSON_UTF8)
						.content(TestUtil.convertObjectToJsonBytes(createTodoItemForTesting(TODO_ID_INVALID,
								TODO_TEXT_ID_3_INVALID, TODO_UPDATE_COMPLETED_ID_3, TODO_UPDATE_CREATEDAT_ID_3))))
				.andExpect(status().isBadRequest());
	}
	////END - test cases for updateTodoItem()

}

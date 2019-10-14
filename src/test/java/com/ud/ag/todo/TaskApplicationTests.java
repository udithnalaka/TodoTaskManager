package com.ud.ag.todo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URLEncoder;

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

import com.ud.ag.todo.controller.TaskController;
import com.ud.ag.todo.service.TaskService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TodoTaskManagerApplication.class)
@WebAppConfiguration
public class TaskApplicationTests {

	private MockMvc taskMockMvc;

	@Autowired
	private TaskService taskService;

	private static final String TASK_API_PATH = "/api/v1/tasks";
	private static final String VALIDATE_BRACKET_API_PATH = "/validateBrackets";
	
	private static final String RESPONSE_PARAM_INPUT = "input";
	private static final String RESPONSE_PARAM_BALANCED = "balanced";
	 
	private static final String TASK_INPUT_VALID = "{([])}";
	private static final String TASK_INPUT_INVALID = "{([a})]";
	private static final String TASK_INPUT_TOO_LING = "{([aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
			+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa})]";
	private static final boolean TASK_BALANCED_TRUE = true;
	private static final boolean TASK_BALANCED_FALSE = false;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		TaskController taskResource = new TaskController(taskService);

		this.taskMockMvc = MockMvcBuilders.standaloneSetup(taskResource).build();
	}
	
	
	@Test
	public void checkBracketsBalancedWithEptyInputShouldReturnValidationError() throws Exception {
		
		taskMockMvc.perform(get(TASK_API_PATH +  VALIDATE_BRACKET_API_PATH ))
			.andExpect(status().isBadRequest());
		
	}

	@Test
	public void checkBracketsBalancedWithVliadBracketCombinationShouldReturnTrue() throws Exception {
		String inputParam = URLEncoder.encode(TASK_INPUT_VALID, "UTF-8");
		
		taskMockMvc.perform(get(TASK_API_PATH +  VALIDATE_BRACKET_API_PATH + "?input=" + inputParam ))
			.andExpect(status().isOk())
			.andExpect(jsonPath(RESPONSE_PARAM_INPUT).value(inputParam))
			.andExpect(jsonPath(RESPONSE_PARAM_BALANCED).value(TASK_BALANCED_TRUE));
		
	}
	
	@Test
	public void checkBracketsBalancedWithInVliadBracketCombinationShouldReturnFalse() throws Exception {
		String inputParam = URLEncoder.encode(TASK_INPUT_INVALID, "UTF-8");
		
		taskMockMvc.perform(get(TASK_API_PATH +  VALIDATE_BRACKET_API_PATH + "?input=" + inputParam ))
			.andExpect(status().isOk())
			.andExpect(jsonPath(RESPONSE_PARAM_INPUT).value(inputParam))
			.andExpect(jsonPath(RESPONSE_PARAM_BALANCED).value(TASK_BALANCED_FALSE));
		
	}
	
	@Test
	public void checkBracketsBalancedWithLargeStringShouldReturnValidationError() throws Exception {
		String inputParam = URLEncoder.encode(TASK_INPUT_TOO_LING, "UTF-8");
		
		taskMockMvc.perform(get(TASK_API_PATH +  VALIDATE_BRACKET_API_PATH + "?input=" + inputParam ))
			.andExpect(status().isBadRequest());
		
	}

}

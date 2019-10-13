package com.ud.ag.todo;

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
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		TaskController taskResource = new TaskController(taskService);

		this.taskMockMvc = MockMvcBuilders.standaloneSetup(taskResource).build();
	}

	@Test
	public void test() throws Exception {
	}

}

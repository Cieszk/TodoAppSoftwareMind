package pl.cieszk.todoapp.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.cieszk.todoapp.controllers.TaskViewController;
import pl.cieszk.todoapp.model.TaskDetailsDto;
import pl.cieszk.todoapp.model.TaskDto;
import pl.cieszk.todoapp.services.TaskService;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class TaskViewControllerTest {

    private MockMvc mockMvc;

    @Mock
    private TaskService taskService;

    @InjectMocks
    private TaskViewController taskViewController;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(taskViewController)
                .build();
    }

    @Test
    public void testViewAllTasks() throws Exception {
        List<TaskDto> tasks = Arrays.asList(new TaskDto(), new TaskDto(), new TaskDto());

        given(taskService.getAllTasks()).willReturn(tasks);

        mockMvc.perform(get("/tasks/"))
                .andExpect(status().isOk())
                .andExpect(view().name("tasks-list"))
                .andExpect(model().attribute("tasks", hasSize(3)));
    }

    @Test
    public void testShowNewTaskForm() throws Exception {
        mockMvc.perform(get("/tasks/new-task"))
                .andExpect(status().isOk())
                .andExpect(view().name("new-task-form"))
                .andExpect(model().attributeExists("newTask"));
    }

    @Test
    public void testViewTaskDetails() throws Exception {
        Long id = 1L;
        TaskDetailsDto taskDetailsDto = new TaskDetailsDto();
        taskDetailsDto.setId(id);

        given(taskService.findTaskDetailsDtoById(id)).willReturn(taskDetailsDto);

        mockMvc.perform(get("/tasks/" + id))
                .andExpect(status().isOk())
                .andExpect(view().name("task-detail"))
                .andExpect(model().attribute("task", equalTo(taskDetailsDto)));
    }

    @Test
    public void testShowTaskEditForm() throws Exception {
        Long id = 1L;
        TaskDetailsDto taskDetailsDto = new TaskDetailsDto();
        taskDetailsDto.setId(id);

        given(taskService.findTaskDetailsDtoById(id)).willReturn(taskDetailsDto);

        mockMvc.perform(get("/tasks/" + id + "/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("task-edit"))
                .andExpect(model().attribute("task", equalTo(taskDetailsDto)));
    }
}
package pl.cieszk.todoapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.cieszk.todoapp.controllers.api.TaskController;
import pl.cieszk.todoapp.model.TaskDetailsDto;
import pl.cieszk.todoapp.model.TaskDto;
import pl.cieszk.todoapp.services.TaskService;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class TaskControllerTest {

    private MockMvc mockMvc;

    @Mock
    private TaskService taskService;

    @InjectMocks
    private TaskController taskController;

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(taskController)
                .build();
    }

    @Test
    public void testGetAllTasks() throws Exception {
        TaskDto task = new TaskDto();
        task.setId(1L);
        task.setTitle("Test");

        List<TaskDto> tasks = Arrays.asList(task);

        given(taskService.getAllTasks()).willReturn(tasks);

        mockMvc.perform(get("/api/tasks/")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(task.getId().intValue())))
                .andExpect(jsonPath("$[0].title", is(task.getTitle())));
    }

    @Test
    public void testGetTaskById() throws Exception {
        Long id = 1L;
        TaskDetailsDto task = new TaskDetailsDto();
        task.setId(id);
        task.setTitle("Test");

        given(taskService.findTaskDetailsDtoById(id)).willReturn(task);

        mockMvc.perform(get("/api/tasks/{id}", id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(task.getId().intValue())))
                .andExpect(jsonPath("$.title", is(task.getTitle())));
    }

    @Test
    public void testGetTaskByIdNotFound() throws Exception {
        Long id = 1L;

        given(taskService.findTaskDetailsDtoById(id)).willReturn(null);

        mockMvc.perform(get("/api/tasks/{id}", id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateTask() throws Exception {
        TaskDetailsDto task = new TaskDetailsDto();
        task.setTitle("Test");
        task.setDescription("Test");

        given(taskService.createTask(task)).willReturn(task);

        mockMvc.perform(post("/api/tasks/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(task))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title", is(task.getTitle())))
                .andExpect(jsonPath("$.description", is(task.getDescription())));
    }

    @Test
    public void testUpdateTask() throws Exception {
        Long id = 1L;
        TaskDetailsDto task = new TaskDetailsDto();
        task.setId(id);
        task.setTitle("Updated test");
        task.setDescription("Updated test");

        given(taskService.updateTask(id, task)).willReturn(task);

        mockMvc.perform(put("/api/tasks/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(task))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(task.getId().intValue())))
                .andExpect(jsonPath("$.title", is(task.getTitle())))
                .andExpect(jsonPath("$.description", is(task.getDescription())));
    }

    @Test
    public void testDeleteTask() throws Exception {
        Long id = 1L;

        given(taskService.deleteTask(id)).willReturn(true);

        mockMvc.perform(delete("/api/tasks/{id}", id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", is(true)));
    }

    @Test
    public void testSetTaskAsDone() throws Exception {
        Long id = 1L;
        TaskDetailsDto task = new TaskDetailsDto();
        task.setId(id);
        task.setTitle("Test");
        task.setDescription("Test");
        task.setDone(true);

        given(taskService.setTaskDone(id, true)).willReturn(task);

        mockMvc.perform(put("/api/tasks/set-as-done/{id}", id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(task.getId().intValue())))
                .andExpect(jsonPath("$.done", is(task.isDone())));
    }
}

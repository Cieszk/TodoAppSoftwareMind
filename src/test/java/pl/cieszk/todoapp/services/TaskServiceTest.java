package pl.cieszk.todoapp.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.cieszk.todoapp.model.TaskDetailsDto;
import pl.cieszk.todoapp.model.TaskDto;
import pl.cieszk.todoapp.model.entity.Task;
import pl.cieszk.todoapp.repositories.TaskRepository;
import pl.cieszk.todoapp.utils.TaskMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private TaskMapper taskMapper;

    @InjectMocks
    private TaskService taskService;

    @Test
    void createTask() {
        TaskDetailsDto taskDetailsDto = new TaskDetailsDto();
        taskDetailsDto.setTitle("Test task");

        Task task = new Task();
        Task savedTask = new Task();
        savedTask.setId(1L);

        when(taskMapper.toEntity(taskDetailsDto)).thenReturn(task);
        when(taskRepository.save(task)).thenReturn(savedTask);
        when(taskMapper.toDetailsDto(savedTask)).thenReturn(taskDetailsDto);

        TaskDetailsDto result = taskService.createTask(taskDetailsDto);

        assertEquals(taskDetailsDto, result);
    }

    @Test
    void getAllTasks() {
        TaskDto taskDto = new TaskDto();
        Task task = new Task();

        when(taskRepository.findAll()).thenReturn(Arrays.asList(task));
        when(taskMapper.toDto(task)).thenReturn(taskDto);

        List<TaskDto> result = taskService.getAllTasks();

        assertFalse(result.isEmpty());
        assertEquals(taskDto, result.get(0));
    }

    @Test
    void findTaskDetailsDtoById() {
        Long id = 1L;
        TaskDetailsDto taskDetailsDto = new TaskDetailsDto();
        Task task = new Task();

        when(taskRepository.findById(id)).thenReturn(Optional.of(task));
        when(taskMapper.toDetailsDto(task)).thenReturn(taskDetailsDto);

        TaskDetailsDto result = taskService.findTaskDetailsDtoById(id);

        assertEquals(taskDetailsDto, result);
    }

    @Test
    void findTaskDetailsDtoById_NotFound() {
        Long id = 1L;

        when(taskRepository.findById(id)).thenReturn(Optional.empty());

        TaskDetailsDto result = taskService.findTaskDetailsDtoById(id);

        assertNull(result);
    }

    @Test
    void deleteTask() {
        Long id = 1L;
        Task task = new Task();

        when(taskRepository.findById(id)).thenReturn(Optional.of(task));

        boolean result = taskService.deleteTask(id);

        assertTrue(result);
        verify(taskRepository).delete(task);
    }

    @Test
    void deleteTask_NotFound() {
        Long id = 1L;

        when(taskRepository.findById(id)).thenReturn(Optional.empty());

        boolean result = taskService.deleteTask(id);

        assertFalse(result);
        verify(taskRepository, never()).delete(any());
    }
}
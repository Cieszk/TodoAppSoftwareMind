package pl.cieszk.todoapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.cieszk.todoapp.model.TaskDetailsDto;
import pl.cieszk.todoapp.model.TaskDto;
import pl.cieszk.todoapp.model.entity.Task;
import pl.cieszk.todoapp.repositories.TaskRepository;
import pl.cieszk.todoapp.utils.TaskMapper;

import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@Service
@Validated
public class TaskService {
    private final TaskRepository taskRepository;

    private final TaskMapper taskMapper;

    @Autowired
    public TaskService(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    public TaskDetailsDto createTask(@Valid TaskDetailsDto taskDetailsDto) {
        if (taskDetailsDto.getTitle() == null || taskDetailsDto.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        Task task = taskMapper.toEntity(taskDetailsDto);
        task = taskRepository.save(task);
        return taskMapper.toDetailsDto(task);
    }

    public List<TaskDto> getAllTasks() {
        List<Task> task = taskRepository.findAll();
        return task.stream()
                .map(taskMapper::toDto)
                .toList();
    }

    public TaskDetailsDto findTaskDetailsDtoById(Long id) {
        Task task = taskRepository.findById(id).orElse(null);
        return task != null ? taskMapper.toDetailsDto(task) : null;
    }

    public List<TaskDto> findAllCompletedTasks() {
        List<Task> tasks = taskRepository.findByDoneIsTrue();
        return tasks.stream()
                .map(taskMapper::toDto)
                .toList();
    }

    public List<TaskDto> findAllIncompleteTasks() {
        List<Task> tasks = taskRepository.findByDoneIsFalse();
        return tasks.stream()
                .map(taskMapper::toDto)
                .toList();
    }

    public boolean deleteTask(Long id) {
        Task toDelete = taskRepository.findById(id).orElse(null);
        if (toDelete != null) {
            taskRepository.delete(toDelete);
            return true;
        }
        return false;
    }

    public TaskDetailsDto updateTask(Long id, @Valid TaskDetailsDto taskDetailsDto) {
        if (taskDetailsDto.getTitle() == null || taskDetailsDto.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        Task taskToUpdate = taskRepository.findById(id).orElse(null);
        if (taskToUpdate == null) {
            return null;
        }
        taskMapper.updateFromDto(taskDetailsDto, taskToUpdate);
        taskToUpdate = taskRepository.save(taskToUpdate);
        return taskMapper.toDetailsDto(taskToUpdate);
    }

    public TaskDetailsDto setTaskDone(Long id, boolean done) {
        Task taskToUpdate = taskRepository.findById(id).orElse(null);
        if (taskToUpdate != null) {
            taskToUpdate.setDone(done);
            taskToUpdate = taskRepository.save(taskToUpdate);
            return taskMapper.toDetailsDto(taskToUpdate);
        }
        return null;
    }


}

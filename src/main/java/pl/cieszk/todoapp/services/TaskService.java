package pl.cieszk.todoapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.cieszk.todoapp.model.entity.Task;
import pl.cieszk.todoapp.repositories.TaskRepository;

import java.util.List;

@Service
public class TaskService {
    TaskRepository taskRepository;
    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task findTaskById(Long id) {
        return taskRepository.getById(id);
    }

    public List<Task> findAllCompletedTasks() {
        return taskRepository.findByDoneIsTrue();
    }

    public List<Task> findAllUnfinishedTasks() {
        return taskRepository.findByDoneIsFalse();
    }

    public void deleteTask(Task task) {
        taskRepository.delete(task);
    }

    public Task updateTask(Task task) {
        return taskRepository.save(task);
    }
}

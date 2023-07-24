package pl.cieszk.todoapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.cieszk.todoapp.model.entity.Task;
import pl.cieszk.todoapp.services.TaskService;

import java.util.List;

@RestController
public class TaskController {

    private TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @GetMapping("/completed")
    public ResponseEntity<List<Task>> getAllCompletedTasks() {
        return ResponseEntity.ok(taskService.findAllCompletedTasks());
    }

    @GetMapping("/incomplete")
    public ResponseEntity<List<Task>> getAllIncompleteTasks() {
        return ResponseEntity.ok(taskService.findAllIncompleteTasks());
    }

    @PostMapping("/")
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        return ResponseEntity.ok(taskService.createTask(task));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task) {
        task.setId(id);
        return ResponseEntity.ok(taskService.updateTask(task));
    }

    @PutMapping("/set_as_done/{id}")
    public ResponseEntity<Task> setTaskAsDone(@PathVariable Long id, @RequestBody Task toUpdate) {
        Task task = taskService.findTaskById(id);
        toUpdate.setId(id);
        toUpdate.setTitle(task.getTitle());
        toUpdate.setDescription(task.getDescription());
        toUpdate.setDone(true);
        return ResponseEntity.ok(taskService.updateTask(toUpdate));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteTask(@PathVariable Long id) {
        Task toDelete = taskService.findTaskById(id);
        taskService.deleteTask(toDelete);
        return ResponseEntity.ok(true);
    }

}

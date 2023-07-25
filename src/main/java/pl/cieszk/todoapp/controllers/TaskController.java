package pl.cieszk.todoapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.cieszk.todoapp.model.TaskDetailsDto;
import pl.cieszk.todoapp.model.TaskDto;
import pl.cieszk.todoapp.services.TaskService;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/")
    public ResponseEntity<List<TaskDto>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDetailsDto> getTaskById(@PathVariable Long id) {
        TaskDetailsDto taskDetailsDto = taskService.findTaskDetailsDtoById(id);
        if (taskDetailsDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(taskDetailsDto);
    }

    @GetMapping("/completed")
    public ResponseEntity<List<TaskDto>> getAllCompletedTasks() {
        List<TaskDto> tasks = taskService.findAllCompletedTasks();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/incomplete")
    public ResponseEntity<List<TaskDto>> getAllIncompleteTasks() {
        List<TaskDto> tasks = taskService.findAllIncompleteTasks();
        return ResponseEntity.ok(tasks);
    }

    @PostMapping("/")
    public ResponseEntity<TaskDetailsDto> createTask(@RequestBody TaskDetailsDto task) {
        return ResponseEntity.ok(taskService.createTask(task));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDetailsDto> updateTask(@PathVariable Long id, @RequestBody TaskDetailsDto task) {
        TaskDetailsDto toUpdate = taskService.updateTask(id, task);
        if (toUpdate == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(toUpdate);
    }

    @PutMapping("/set-as-done/{id}")
    public ResponseEntity<TaskDetailsDto> setTaskAsDone(@PathVariable Long id) {
        TaskDetailsDto task = taskService.setTaskDone(id, true);
        if (task == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(task);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteTask(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.deleteTask(id));
    }

}

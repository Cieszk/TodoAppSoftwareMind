package pl.cieszk.todoapp.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.cieszk.todoapp.model.TaskDetailsDto;
import pl.cieszk.todoapp.model.TaskDto;
import pl.cieszk.todoapp.services.TaskService;

import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskViewController {
    private final TaskService taskService;

    @Autowired
    public TaskViewController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/")
    public ModelAndView viewAllTasks(Model model) {
        List<TaskDto> tasks = taskService.getAllTasks();
        model.addAttribute("tasks", tasks);
        return new ModelAndView("tasks-list");
    }

    @GetMapping("/new-task")
    public ModelAndView showNewTaskForm(Model model) {
        TaskDetailsDto newTask = new TaskDetailsDto();
        return new ModelAndView("new-task-form", "newTask", newTask);
    }

    @GetMapping("/{id}")
    public ModelAndView viewTaskDetails(@PathVariable Long id) {
        TaskDetailsDto task = taskService.findTaskDetailsDtoById(id);
        return new ModelAndView("task-detail", "task", task);
    }

    @PostMapping("/update/{id}")
    public String updateTask(@PathVariable Long id, @ModelAttribute @Valid TaskDetailsDto task) {
        TaskDetailsDto updatedTask = taskService.updateTask(id, task);
        return "redirect:/tasks/" + id;
    }

    @GetMapping("/{id}/edit")
    public ModelAndView showTaskEditForm(@PathVariable Long id) {
        TaskDetailsDto task = taskService.findTaskDetailsDtoById(id);
        return new ModelAndView("task-edit", "task", task);
    }

    @PostMapping("/new-task")
    public String createNewTask(@ModelAttribute("newTask") @Valid TaskDetailsDto taskDetailsDto) {
        TaskDetailsDto task = taskService.createTask(taskDetailsDto);
        return String.format("redirect:/tasks/%d", task.getId());
    }

    @PostMapping("/set-as-done/{id}")
    public String setTaskAsDone(@PathVariable Long id) {
        TaskDetailsDto task = taskService.setTaskDone(id, true);
        return "redirect:/tasks/";
    }

    @PostMapping("/{id}/remove")
    public String removeTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "redirect:/tasks/";
    }
}

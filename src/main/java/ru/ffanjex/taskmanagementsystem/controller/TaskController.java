package ru.ffanjex.taskmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ffanjex.taskmanagementsystem.model.Task;
import ru.ffanjex.taskmanagementsystem.service.ProjectService;
import ru.ffanjex.taskmanagementsystem.service.TaskService;

@Controller
public class TaskController {
    private final TaskService taskService;
    private final ProjectService projectService;

    @Autowired
    public TaskController(TaskService taskService, ProjectService projectService) {
        this.taskService = taskService;
        this.projectService = projectService;
    }

    @GetMapping("/add-task")
    public String addTaskForm() {
        return "add-task";
    }

    @PostMapping("/add-task")
    public String addTask(@RequestParam("name") String name, @RequestParam("description") String description,
                          @RequestParam("completed") boolean completed,
                          Model model) {
        Task task = new Task();
        task.setName(name);
        task.setDescription(description);
        task.setCompleted(completed);
        taskService.create(task);

        model.addAttribute("message", "Задача успешно добавлена");
        return "redirect:/view-projects";
    }
}

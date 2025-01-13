package ru.ffanjex.taskmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ffanjex.taskmanagementsystem.model.Project;
import ru.ffanjex.taskmanagementsystem.model.Task;
import ru.ffanjex.taskmanagementsystem.service.ProjectService;
import ru.ffanjex.taskmanagementsystem.service.TaskService;

import java.util.List;
import java.util.Optional;

@Controller
public class ProjectController {

    private final ProjectService projectService;
    private final TaskService taskService;

    @Autowired
    public ProjectController(ProjectService projectService, TaskService taskService) {
        this.projectService = projectService;
        this.taskService = taskService;
    }

    @GetMapping("/services")
    public String services() {
        return "services";
    }

    @GetMapping("/add-project")
    public String addProjectForm() {
        return "actionsWithProject/add-project";
    }

    @PostMapping("/add-project")
    public String addProject(@RequestParam("name") String name,
                             @RequestParam(value = "description", required = false) String description,
                             Model model) {
        Project project = new Project();
        project.setName(name);
        project.setDescription(description);
        projectService.create(project);
        model.addAttribute("message", "Проект успешно добавлен");
        return "redirect:/view-projects";
    }

    @GetMapping("/view-projects")
    public String viewAllProjects(Model model) {
        List<Project> projects = projectService.getAllProjects();
        model.addAttribute("projects", projects);
        return "actionsWithProject/view-projects";
    }

    @GetMapping("/edit-project/{id}")
    public String editProjectForm(@PathVariable Integer id, Model model) {
        Optional<Project> projectOptional = projectService.findById(id);
        if (projectOptional.isPresent()) {
            Project project = projectOptional.get();
            model.addAttribute("project", project);
            return "actionsWithProject/edit-project";
        }
        model.addAttribute("error", "Проект с таким ID не найден");
        return "error";
    }

    @PostMapping("/edit-project/{id}")
    public String updateProject(@PathVariable Integer id,
                                @RequestParam("name") String name,
                                @RequestParam(value = "description", required = false) String description) {
        Optional<Project> projectOptional = projectService.findById(id);
        if (projectOptional.isPresent()) {
            Project project = projectOptional.get();
            project.setName(name);
            project.setDescription(description);
            projectService.update(project);
        }
        return "redirect:/view-projects";
    }

    @GetMapping("/delete-project/{id}")
    public String deleteProject(@PathVariable Integer id) {
        projectService.delete(id);
        return "redirect:/view-projects";
    }

    @GetMapping("/view-tasks/{id}")
    public String viewProjectTasks(@PathVariable Integer id, Model model) {
        Optional<Project> projectOptional = projectService.findById(id);

        if (projectOptional.isPresent()) {
            Project project = projectOptional.get();
            model.addAttribute("project", project);
            model.addAttribute("tasks", project.getTasks());
            return "/actionsWithProject/view-tasks";
        }
        model.addAttribute("error", "Проект с таким ID не найден");
        return "error";
    }

    @PostMapping("/toggle-task-status/{taskId}")
    public String toggleTaskStatus(@PathVariable Integer taskId) {
        Optional<Task> optionalTask = taskService.findById(taskId);

        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setCompleted(!task.isCompleted());
            taskService.update(task);
        }

        return "redirect:/about-project/" + optionalTask.map(Task::getProjectId).orElse(0);
    }
}

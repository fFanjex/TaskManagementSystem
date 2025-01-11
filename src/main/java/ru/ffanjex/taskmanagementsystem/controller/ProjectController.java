package ru.ffanjex.taskmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ffanjex.taskmanagementsystem.model.Project;
import ru.ffanjex.taskmanagementsystem.service.ProjectService;

import java.util.List;

@Controller
public class ProjectController {

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
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
        return "redirect:/services";
    }

    @GetMapping("/view-projects")
    public String viewAllProjects(Model model) {
        List<Project> projects = projectService.getAllProjects();
        model.addAttribute("projects", projects);
        return "actionsWithProject/view-projects";
    }
}

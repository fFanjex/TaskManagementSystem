package ru.ffanjex.taskmanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ffanjex.taskmanagementsystem.model.Project;
import ru.ffanjex.taskmanagementsystem.repository.ProjectRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project create(Project project) {
        return projectRepository.save(project);
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Optional<Project> findById(Integer id) {
        return projectRepository.findById(id);
    }

    public void update(Project project) {
        projectRepository.save(project);
    }

    public void delete(Integer id) {
        projectRepository.deleteById(id);
    }

}

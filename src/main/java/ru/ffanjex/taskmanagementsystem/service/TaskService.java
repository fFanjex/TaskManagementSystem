package ru.ffanjex.taskmanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ffanjex.taskmanagementsystem.model.Task;
import ru.ffanjex.taskmanagementsystem.repository.TaskRepository;

import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Optional<Task> findById(Integer id) {
        return taskRepository.findById(id);
    }

    public void update(Task task) {
        taskRepository.save(task);
    }

    public Task create(Task task) {
        return taskRepository.save(task);
    }
}
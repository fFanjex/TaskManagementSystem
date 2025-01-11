package ru.ffanjex.taskmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ffanjex.taskmanagementsystem.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
}

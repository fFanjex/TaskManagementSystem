package ru.ffanjex.taskmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ffanjex.taskmanagementsystem.model.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
}

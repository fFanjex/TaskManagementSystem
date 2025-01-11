package ru.ffanjex.taskmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ffanjex.taskmanagementsystem.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}

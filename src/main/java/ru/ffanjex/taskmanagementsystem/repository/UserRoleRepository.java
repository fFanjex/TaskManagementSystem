package ru.ffanjex.taskmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ffanjex.taskmanagementsystem.model.UserRole;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
}

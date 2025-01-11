package ru.ffanjex.taskmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ffanjex.taskmanagementsystem.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}

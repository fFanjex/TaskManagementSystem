package ru.ffanjex.taskmanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.ffanjex.taskmanagementsystem.model.Role;
import ru.ffanjex.taskmanagementsystem.model.User;
import ru.ffanjex.taskmanagementsystem.model.UserRole;
import ru.ffanjex.taskmanagementsystem.repository.RoleRepository;
import ru.ffanjex.taskmanagementsystem.repository.UserRepository;
import ru.ffanjex.taskmanagementsystem.repository.UserRoleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository,
                       UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(String username, String password, String roleName) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("Пользователь с таким именем уже существует");
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user = userRepository.save(user);

        Role role = roleRepository.findByName(roleName);
        if (role == null) {
            role = new Role();
            role.setName(roleName);
            role = roleRepository.save(role);
        }

        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);
        userRoleRepository.save(userRole);

        return user;
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}

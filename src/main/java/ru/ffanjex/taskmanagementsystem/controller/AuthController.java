package ru.ffanjex.taskmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ffanjex.taskmanagementsystem.service.UserService;

@Controller
public class AuthController {
    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password,
                        Model model) {
        var user = userService.findByUsername(username);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return "redirect:/";
        }
        model.addAttribute("error", "Неверное имя пользователя");
        return "login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password,
                           @RequestParam String role, Model model) {
        try {
            userService.registerUser(username, password, role);
            return "redirect:/login";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }
}

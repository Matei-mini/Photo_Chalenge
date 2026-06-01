package com.example.demo.controller;

import com.example.demo.models.Users;
import com.example.demo.service.UsersService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UsersController {
    private final UsersService usersService;

    public UsersController(UsersService usersService){
        this.usersService = usersService;
    }

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model, HttpSession session){
        Users user = usersService.login(username, password);
        if (user != null){
            session.setAttribute("userId", user.getId());
            return "redirect:/pictures";
        }
        model.addAttribute("error", "Invalid user or pass.");
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(){
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password, Model model){
        Users user = usersService.register(username, password);
        if (user != null){
            return "redirect:/users/login";
        }
        model.addAttribute("error", "Used name or insufficient input.");
        return "register";
    }
}

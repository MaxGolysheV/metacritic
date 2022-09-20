package com.example.metacritic.Controllers;

import com.example.metacritic.Models.Role;
import com.example.metacritic.Models.User;
import com.example.metacritic.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/registration")
    public String registration()
    {
        return "registration";//
    }

    @PostMapping("/registration")
    public String addUser(User user, Model model,
                          @RequestParam String username,
                          @RequestParam String password)
    {

        User userForm = userRepository.findByUsername(user.getUsername());
        if(username=="" || password=="")
        {
            model.addAttribute("message", "Заполните поля");
            return "registration";
        }

        if(userForm != null)
        {
            model.addAttribute("message", "Пользователь уже такой есть");
            return "registration";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.ADMIN));
        userRepository.save(user);
        return "redirect:/login";//
    }
}

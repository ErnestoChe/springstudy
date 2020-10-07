package com.example.demo.controllers;

import com.example.demo.domain.Role;
import com.example.demo.domain.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/registration")
    public String reg(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> map){
        User userByLogin = userRepository.findByUsername(user.getUsername());
        if(userByLogin !=null){
            map.put("message", "user exists");
            return "registration";
        }
        user.setRoles(Collections.singleton(Role.USER));
        user.setActive(true);
        userRepository.save(user);
        return "redirect:/login";
    }
}

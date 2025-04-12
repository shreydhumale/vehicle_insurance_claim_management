package com.example.vehicle.controller;

import com.example.vehicle.model.User;
import com.example.vehicle.service.UserService;
import com.example.vehicle.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;  

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;  

    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        System.out.println("Received registration request: " + user.getUsername());

        Optional<User> existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser.isPresent()) {
            return "Username already exists!";
        }

        userService.saveUser(user); 
        return "User " + user.getUsername() + " registered successfully!";
    }


    @PostMapping("/login")
    public Map<String, String> loginUser(@RequestBody User user) {
        Optional<User> foundUser = userRepository.findByUsername(user.getUsername());

        Map<String, String> response = new HashMap<>();
        if (foundUser.isPresent() && passwordEncoder.matches(user.getPassword(), foundUser.get().getPassword())) { 
            response.put("message", "Login successful");
            response.put("username", foundUser.get().getUsername());
            response.put("role", foundUser.get().getRole()); 
        } else {
            response.put("message", "Invalid username or password");
        }
        return response;
    }


}
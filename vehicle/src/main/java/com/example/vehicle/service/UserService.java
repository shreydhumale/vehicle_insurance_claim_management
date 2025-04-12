package com.example.vehicle.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.vehicle.model.User;
import com.example.vehicle.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;  
    @Transactional
    public User saveUser(User user) {
        System.out.println("Original Password: " + user.getPassword());

        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);

        System.out.println("Hashed Password: " + hashedPassword);

        return userRepository.save(user);
    }
}
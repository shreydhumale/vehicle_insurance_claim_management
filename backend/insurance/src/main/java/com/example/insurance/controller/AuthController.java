package com.example.insurance.controller;

import com.example.insurance.model.User;
import com.example.insurance.security.CustomUserDetailsService;
import com.example.insurance.security.JwtUtil;
import com.example.insurance.service.UserService;

import io.jsonwebtoken.lang.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtUtil jwtUtil;
    
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
    private UserService userService;
    
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }
    
//    @PostMapping("/login")
//    public ResponseEntity<?> loginUser(@RequestBody User loginRequest) {
//        try {
//            // Authenticate the user
//            authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
//            );
//
//            // Load user details
//            UserDetails userDetails = customUserDetailsService.loadUserByUsername(loginRequest.getUsername());
//
//            // Generate JWT token
//            String token = jwtUtil.generateToken(userDetails);
//
//            // Return the token
//            return ResponseEntity.ok(Collections.singletonMap("token", token));
//        } catch (BadCredentialsException e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
//        }
//    }
}
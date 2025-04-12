package com.example.insurance.controller;

import com.example.insurance.model.Claim;
import com.example.insurance.model.User;
import com.example.insurance.repository.ClaimRepository;
import com.example.insurance.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/claims")
@CrossOrigin(origins = "http://localhost:3000") // Allow front-end to access this API

public class ClaimController {
    @Autowired
    private ClaimRepository claimRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public Claim createClaim(@RequestBody Claim claim) {
        System.out.println("Received claim: " + claim);

        // Fetch the user from the database
        User user = userRepository.findById(claim.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Set the user in the claim
        claim.setUser(user);

        // Set default values
        claim.setCreatedAt(LocalDateTime.now());
        claim.setClaimStatus("PENDING");

        // Save the claim
        return claimRepository.save(claim);
        
    }

    // GET request to fetch claims by user ID
    @GetMapping("/user/{userId}")
    public List<Claim> getClaimsByUser(@PathVariable Long userId) {
        return claimRepository.findByUser_Id(userId);
    }
    
    
}
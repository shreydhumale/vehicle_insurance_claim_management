package com.example.vehicle.controller;

import com.example.vehicle.model.Claim;
import com.example.vehicle.repository.ClaimRepository;
import com.example.vehicle.model.User;
import com.example.vehicle.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/claims")
@CrossOrigin(origins = "http://localhost:8081")
public class ClaimController {

    @Autowired
    private ClaimRepository claimRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/submit")
    public String submitClaim(@RequestBody Claim claim, @RequestParam String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            claim.setUser(user.get());
            claimRepository.save(claim);
            return "Claim submitted successfully!";
        } else {
            return "User not found!";
        }
    }

    @GetMapping("/user/{username}")
    public List<Claim> getUserClaims(@PathVariable String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.map(claimRepository::findByUser).orElse(null);
    }

    @GetMapping("/all")
    public List<Claim> getAllClaims() {
        return claimRepository.findAll();
    }

    
    @PutMapping("/{id}/update-status")
    public String updateClaimStatus(@PathVariable Long id, @RequestParam String status) {
        Optional<Claim> claim = claimRepository.findById(id);
        if (claim.isPresent()) {
            claim.get().setClaimStatus(status);
            claimRepository.save(claim.get());
            return "Claim status updated successfully!";
        }
        return "Claim not found!";
    }
}

package com.example.vehicle.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.vehicle.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
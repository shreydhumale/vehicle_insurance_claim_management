package com.example.vehicle.repository;

import com.example.vehicle.model.Claim;
import com.example.vehicle.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ClaimRepository extends JpaRepository<Claim, Long> {
    List<Claim> findByUser(User user);

    List<Claim> findByClaimStatus(String status);
}
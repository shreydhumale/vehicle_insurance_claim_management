package com.example.insurance.repository;

import com.example.insurance.model.Claim;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ClaimRepository extends JpaRepository<Claim, Long> {
    List<Claim> findByUser_Id(Long userId); // Correct method name
}
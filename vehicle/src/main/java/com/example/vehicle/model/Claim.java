package com.example.vehicle.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Claim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String vehicleInfo;

    @Column(nullable = false)
    private String accidentDescription;

    @Column(nullable = false)
    private String claimStatus = "PENDING";

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

	public Claim() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Claim(Long id, String vehicleInfo, String accidentDescription, String claimStatus, LocalDateTime createdAt,
			User user) {
		super();
		this.id = id;
		this.vehicleInfo = vehicleInfo;
		this.accidentDescription = accidentDescription;
		this.claimStatus = claimStatus;
		this.createdAt = createdAt;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVehicleInfo() {
		return vehicleInfo;
	}

	public void setVehicleInfo(String vehicleInfo) {
		this.vehicleInfo = vehicleInfo;
	}

	public String getAccidentDescription() {
		return accidentDescription;
	}

	public void setAccidentDescription(String accidentDescription) {
		this.accidentDescription = accidentDescription;
	}

	public String getClaimStatus() {
		return claimStatus;
	}

	public void setClaimStatus(String claimStatus) {
		this.claimStatus = claimStatus;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

    
}
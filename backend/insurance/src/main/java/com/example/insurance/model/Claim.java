package com.example.insurance.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;


@Entity
public class Claim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vehicle_info", nullable = false)
    private String vehicleInfo;

    @Column(name = "accident_description", nullable = false)
    private String accidentDescription;

    @Column(name = "claim_status", nullable = false)
    private String claimStatus = "PENDING";

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Transient // This field is not persisted in the database
    private Long userId; // Used to accept userId from the front-end

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // Used to store the User entity

    public Claim(){
    	super();
    }
	public Claim(Long id, String vehicleInfo, String accidentDescription, String claimStatus, LocalDateTime createdAt,
			LocalDateTime updatedAt, Long userId, User user) {
		super();
		this.id = id;
		this.vehicleInfo = vehicleInfo;
		this.accidentDescription = accidentDescription;
		this.claimStatus = claimStatus;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.userId = userId;
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

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
}
package com.tripease.travelbooking.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "package_likes", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"package_id", "user_identifier"})
})
public class PackageLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "package_id", nullable = false, length = 60)
    private String packageId;

    @Column(name = "user_identifier", nullable = false, length = 120)
    private String userIdentifier;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    public PackageLike() {}

    public PackageLike(String packageId, String userIdentifier) {
        this.packageId = packageId;
        this.userIdentifier = userIdentifier;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPackageId() { return packageId; }
    public void setPackageId(String packageId) { this.packageId = packageId; }

    public String getUserIdentifier() { return userIdentifier; }
    public void setUserIdentifier(String userIdentifier) { this.userIdentifier = userIdentifier; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}


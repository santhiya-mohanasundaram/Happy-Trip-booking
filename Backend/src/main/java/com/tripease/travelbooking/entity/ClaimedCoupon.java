package com.tripease.travelbooking.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "claimed_coupons", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"userIdentifier", "couponCode"})
})
public class ClaimedCoupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String userIdentifier;

    @Column(nullable = false, length = 30)
    private String couponCode;

    private Double discountPercent;

    private Double maxDiscount;

    private LocalDateTime claimedAt;

    private Boolean used = false;

    public ClaimedCoupon() {
        this.claimedAt = LocalDateTime.now();
    }

    public ClaimedCoupon(String userIdentifier, String couponCode, Double discountPercent, Double maxDiscount) {
        this.userIdentifier = userIdentifier;
        this.couponCode = couponCode;
        this.discountPercent = discountPercent;
        this.maxDiscount = maxDiscount;
        this.claimedAt = LocalDateTime.now();
        this.used = false;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUserIdentifier() { return userIdentifier; }
    public void setUserIdentifier(String userIdentifier) { this.userIdentifier = userIdentifier; }

    public String getCouponCode() { return couponCode; }
    public void setCouponCode(String couponCode) { this.couponCode = couponCode; }

    public Double getDiscountPercent() { return discountPercent; }
    public void setDiscountPercent(Double discountPercent) { this.discountPercent = discountPercent; }

    public Double getMaxDiscount() { return maxDiscount; }
    public void setMaxDiscount(Double maxDiscount) { this.maxDiscount = maxDiscount; }

    public LocalDateTime getClaimedAt() { return claimedAt; }
    public void setClaimedAt(LocalDateTime claimedAt) { this.claimedAt = claimedAt; }

    public Boolean getUsed() { return used; }
    public void setUsed(Boolean used) { this.used = used; }
}


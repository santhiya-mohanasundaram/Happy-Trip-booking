package com.tripease.travelbooking.repository;

import com.tripease.travelbooking.entity.ClaimedCoupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClaimedCouponRepository extends JpaRepository<ClaimedCoupon, Long> {
    Optional<ClaimedCoupon> findByUserIdentifierAndCouponCode(String userIdentifier, String couponCode);
    boolean existsByUserIdentifierAndCouponCode(String userIdentifier, String couponCode);
    List<ClaimedCoupon> findByUserIdentifier(String userIdentifier);
}


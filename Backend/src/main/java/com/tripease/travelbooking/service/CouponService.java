package com.tripease.travelbooking.service;

import com.tripease.travelbooking.dto.CouponValidationResponse;
import com.tripease.travelbooking.entity.ClaimedCoupon;
import com.tripease.travelbooking.entity.Coupon;
import com.tripease.travelbooking.exception.BadRequestException;
import com.tripease.travelbooking.repository.ClaimedCouponRepository;
import com.tripease.travelbooking.repository.CouponRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CouponService {

    private final CouponRepository couponRepository;
    private final ClaimedCouponRepository claimedCouponRepository;

    public CouponService(CouponRepository couponRepository, ClaimedCouponRepository claimedCouponRepository) {
        this.couponRepository = couponRepository;
        this.claimedCouponRepository = claimedCouponRepository;
    }

    public CouponValidationResponse validateCoupon(String code, Double amount) {
        return validateCoupon(code, amount, null);
    }

    public CouponValidationResponse validateCoupon(String code, Double amount, String userIdentifier) {
        if (code == null || code.trim().isBlank()) {
            return CouponValidationResponse.invalid("", amount, "Please enter a valid coupon code");
        }

        String normalizedCode = code.trim().toUpperCase();
        Double baseAmount = amount != null ? amount : 0.0;

        Optional<Coupon> opt = couponRepository.findByCodeIgnoreCaseAndActiveTrue(normalizedCode);
        Double discountPercent = 10.0;
        Double maxDiscount = 10000.0;

        if (opt.isPresent()) {
            Coupon coupon = opt.get();
            discountPercent = coupon.getDiscountPercent();
            maxDiscount = coupon.getMaxDiscount();
        } else if ("TRIPEASE2024".equals(normalizedCode)) {
            discountPercent = 15.0;
            maxDiscount = 5000.0;
        } else if (!"TRAVEL10".equals(normalizedCode)) {
            return CouponValidationResponse.invalid(normalizedCode, baseAmount, "Invalid or expired coupon code. Use TRAVEL10 or TRIPEASE2024.");
        }

        double discount = Math.round(baseAmount * (discountPercent / 100.0));
        if (maxDiscount != null && discount > maxDiscount) {
            discount = maxDiscount;
        }
        double finalPrice = Math.max(0.0, baseAmount - discount);

        return CouponValidationResponse.valid(
                normalizedCode,
                discountPercent,
                discount,
                baseAmount,
                finalPrice,
                "Coupon " + normalizedCode + " applied! You saved ₹" + (long) discount
        );
    }

    @Transactional
    public ClaimedCoupon claimCoupon(String code, String userIdentifier) {
        if (userIdentifier == null || userIdentifier.trim().isBlank()) {
            throw new BadRequestException("User authentication required to claim coupons.");
        }

        String normalizedCode = (code != null && !code.trim().isBlank()) ? code.trim().toUpperCase() : "TRIPEASE2024";
        String normalizedUser = userIdentifier.trim().toLowerCase();

        // Check if already claimed
        if (claimedCouponRepository.existsByUserIdentifierAndCouponCode(normalizedUser, normalizedCode)) {
            throw new BadRequestException("You have already claimed coupon " + normalizedCode + ".");
        }

        Optional<Coupon> opt = couponRepository.findByCodeIgnoreCaseAndActiveTrue(normalizedCode);
        Double discountPercent = 10.0;
        Double maxDiscount = 10000.0;

        if (opt.isPresent()) {
            discountPercent = opt.get().getDiscountPercent();
            maxDiscount = opt.get().getMaxDiscount();
        } else if ("TRIPEASE2024".equals(normalizedCode)) {
            discountPercent = 15.0;
            maxDiscount = 2000.0;
        }

        ClaimedCoupon claimed = new ClaimedCoupon(normalizedUser, normalizedCode, discountPercent, maxDiscount);
        return claimedCouponRepository.save(claimed);
    }

    public List<ClaimedCoupon> getUserClaimedCoupons(String userIdentifier) {
        if (userIdentifier == null || userIdentifier.trim().isBlank()) return List.of();
        return claimedCouponRepository.findByUserIdentifier(userIdentifier.trim().toLowerCase());
    }

    public boolean hasUserClaimedCoupon(String userIdentifier, String code) {
        if (userIdentifier == null || userIdentifier.trim().isBlank()) return false;
        String normalizedCode = (code != null) ? code.trim().toUpperCase() : "TRIPEASE2024";
        return claimedCouponRepository.existsByUserIdentifierAndCouponCode(userIdentifier.trim().toLowerCase(), normalizedCode);
    }
}

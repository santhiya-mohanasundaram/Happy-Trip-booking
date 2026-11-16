package com.tripease.travelbooking.controller;

import com.tripease.travelbooking.dto.ApiResponse;
import com.tripease.travelbooking.dto.CouponValidationRequest;
import com.tripease.travelbooking.dto.CouponValidationResponse;
import com.tripease.travelbooking.entity.ClaimedCoupon;
import com.tripease.travelbooking.service.CouponService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/coupons")
public class CouponController {

    private final CouponService couponService;

    public CouponController(CouponService couponService) {
        this.couponService = couponService;
    }

    @PostMapping("/validate")
    public ResponseEntity<ApiResponse<CouponValidationResponse>> validateCoupon(
            @RequestBody(required = false) CouponValidationRequest req,
            @RequestParam(required = false) String code,
            @RequestParam(required = false) Double amount) {

        String couponCode = (req != null && req.getCode() != null) ? req.getCode() : code;
        Double baseAmount = (req != null && req.getAmount() != null) ? req.getAmount() : amount;

        CouponValidationResponse response = couponService.validateCoupon(couponCode, baseAmount);
        return ResponseEntity.ok(ApiResponse.ok("Coupon validated", response));
    }

    @GetMapping("/validate")
    public ResponseEntity<ApiResponse<CouponValidationResponse>> validateCouponGet(
            @RequestParam String code,
            @RequestParam(defaultValue = "0") Double amount) {
        CouponValidationResponse response = couponService.validateCoupon(code, amount);
        return ResponseEntity.ok(ApiResponse.ok("Coupon validated", response));
    }

    @PostMapping("/claim")
    public ResponseEntity<ApiResponse<ClaimedCoupon>> claimCoupon(
            @RequestBody(required = false) Map<String, String> body,
            @RequestParam(required = false) String code,
            @RequestParam(required = false) String userIdentifier) {

        String couponCode = (body != null && body.containsKey("code")) ? body.get("code") : code;
        String user = (body != null && body.containsKey("userIdentifier")) ? body.get("userIdentifier") : userIdentifier;

        ClaimedCoupon claimed = couponService.claimCoupon(couponCode, user);
        return ResponseEntity.ok(ApiResponse.ok("Coupon successfully claimed!", claimed));
    }

    @GetMapping("/user")
    public ResponseEntity<ApiResponse<List<ClaimedCoupon>>> getUserClaimedCoupons(@RequestParam String userIdentifier) {
        List<ClaimedCoupon> list = couponService.getUserClaimedCoupons(userIdentifier);
        return ResponseEntity.ok(ApiResponse.ok("User claimed coupons retrieved", list));
    }

    @GetMapping("/user/check")
    public ResponseEntity<ApiResponse<Boolean>> checkClaimedStatus(
            @RequestParam String userIdentifier,
            @RequestParam(defaultValue = "TRIPEASE2024") String code) {
        boolean claimed = couponService.hasUserClaimedCoupon(userIdentifier, code);
        return ResponseEntity.ok(ApiResponse.ok("Claim status checked", claimed));
    }
}

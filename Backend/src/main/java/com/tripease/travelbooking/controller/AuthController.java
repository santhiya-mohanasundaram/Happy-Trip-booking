package com.tripease.travelbooking.controller;

import com.tripease.travelbooking.dto.ApiResponse;
import com.tripease.travelbooking.dto.AuthRequest;
import com.tripease.travelbooking.dto.AuthResponse;
import com.tripease.travelbooking.dto.RegisterRequest;
import com.tripease.travelbooking.dto.UserProfileDTO;
import com.tripease.travelbooking.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<AuthResponse>> register(@Valid @RequestBody RegisterRequest req) {
        AuthResponse response = authService.register(req);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Registration successful", response));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(@Valid @RequestBody AuthRequest req) {
        AuthResponse response = authService.login(req);
        return ResponseEntity.ok(ApiResponse.ok("Login successful", response));
    }

    @GetMapping("/profile")
    public ResponseEntity<ApiResponse<UserProfileDTO>> getProfile(@RequestParam(required = false) Long userId,
                                                                 @RequestParam(required = false) String email) {
        UserProfileDTO profile;
        if (userId != null) {
            profile = authService.getProfile(userId);
        } else if (email != null && !email.isBlank()) {
            profile = authService.getProfileByIdentifier(email);
        } else {
            return ResponseEntity.badRequest().body(ApiResponse.error("Must provide either userId or email"));
        }
        return ResponseEntity.ok(ApiResponse.ok("Profile retrieved", profile));
    }

    @PutMapping("/profile/{userId}")
    public ResponseEntity<ApiResponse<UserProfileDTO>> updateProfile(@PathVariable Long userId,
                                                                     @Valid @RequestBody UserProfileDTO dto) {
        UserProfileDTO updated = authService.updateProfile(userId, dto);
        return ResponseEntity.ok(ApiResponse.ok("Profile updated successfully", updated));
    }

    @GetMapping("/users")
    public ResponseEntity<ApiResponse<java.util.List<UserProfileDTO>>> getAllUsers() {
        java.util.List<UserProfileDTO> list = authService.getAllUsers();
        return ResponseEntity.ok(ApiResponse.ok("Users retrieved successfully", list));
    }
}


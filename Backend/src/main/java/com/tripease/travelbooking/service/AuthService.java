package com.tripease.travelbooking.service;

import com.tripease.travelbooking.dto.AuthRequest;
import com.tripease.travelbooking.dto.AuthResponse;
import com.tripease.travelbooking.dto.RegisterRequest;
import com.tripease.travelbooking.dto.UserProfileDTO;
import com.tripease.travelbooking.entity.User;
import com.tripease.travelbooking.exception.BadRequestException;
import com.tripease.travelbooking.exception.ResourceNotFoundException;
import com.tripease.travelbooking.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthResponse register(RegisterRequest req) {
        if (userRepository.existsByEmail(req.getEmail())) {
            throw new BadRequestException("An account with email " + req.getEmail() + " already exists");
        }

        String encodedPassword = passwordEncoder.encode(req.getPassword());
        User user = new User(
                req.getName(),
                req.getEmail(),
                req.getPhone(),
                encodedPassword,
                "USER"
        );

        User saved = userRepository.save(user);
        String token = "TE-TOKEN-" + UUID.randomUUID();

        return new AuthResponse(saved.getId(), saved.getName(), saved.getEmail(), saved.getPhone(), saved.getRole(), token);
    }

    public AuthResponse login(AuthRequest req) {
        User user = userRepository.findByEmail(req.getEmail())
                .orElseThrow(() -> new BadRequestException("Invalid email or password"));

        if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
            throw new BadRequestException("Invalid email or password");
        }

        String token = "TE-TOKEN-" + UUID.randomUUID();
        return new AuthResponse(user.getId(), user.getName(), user.getEmail(), user.getPhone(), user.getRole(), token);
    }

    public UserProfileDTO getProfile(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));
        return new UserProfileDTO(user.getId(), user.getName(), user.getEmail(), user.getPhone(), user.getRole());
    }

    public UserProfileDTO getProfileByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email " + email));
        return new UserProfileDTO(user.getId(), user.getName(), user.getEmail(), user.getPhone(), user.getRole());
    }

    public UserProfileDTO updateProfile(Long userId, UserProfileDTO dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));

        if (dto.getName() != null && !dto.getName().isBlank()) {
            user.setName(dto.getName());
        }
        if (dto.getPhone() != null && !dto.getPhone().isBlank()) {
            user.setPhone(dto.getPhone());
        }

        User updated = userRepository.save(user);
        return new UserProfileDTO(updated.getId(), updated.getName(), updated.getEmail(), updated.getPhone(), updated.getRole());
    }
}


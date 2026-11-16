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

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthResponse register(RegisterRequest req) {
        if (req.getPhone() == null || req.getPhone().isBlank()) {
            throw new BadRequestException("Phone number is required");
        }

        if (userRepository.existsByPhone(req.getPhone().trim())) {
            throw new BadRequestException("An account with phone number " + req.getPhone() + " already exists");
        }

        if (req.getConfirmPassword() != null && !req.getConfirmPassword().isBlank()) {
            if (!req.getPassword().equals(req.getConfirmPassword())) {
                throw new BadRequestException("Passwords do not match");
            }
        }

        String email = req.getEmail();
        if (email == null || email.isBlank()) {
            String sanitizedPhone = req.getPhone().replaceAll("[^0-9]", "");
            email = "user_" + sanitizedPhone + "@tripease.local";
        }

        if (userRepository.existsByEmail(email)) {
            throw new BadRequestException("An account with email " + email + " already exists");
        }

        String username = req.getUsername();
        if (username == null || username.isBlank()) {
            username = req.getName().toLowerCase().replaceAll("\\s+", "_");
        }

        String encodedPassword = passwordEncoder.encode(req.getPassword());
        User user = new User(
                req.getName().trim(),
                username,
                email.trim(),
                req.getPhone().trim(),
                encodedPassword,
                "USER"
        );

        User saved = userRepository.save(user);
        String token = "TE-TOKEN-" + UUID.randomUUID();

        return new AuthResponse(saved.getId(), saved.getName(), saved.getEmail(), saved.getPhone(), saved.getRole(), token);
    }

    public AuthResponse login(AuthRequest req) {
        String identifier = req.getIdentifier();
        if (identifier.isBlank()) {
            throw new BadRequestException("Username, email, or phone is required");
        }

        // Dedicated check for temporary Admin credentials
        if ("admin".equalsIgnoreCase(identifier) && "Admin@123".equals(req.getPassword())) {
            User admin = userRepository.findByIdentifier("admin").orElse(null);
            if (admin == null) {
                admin = new User("System Admin", "admin", "admin@tripease.com", "+91 98765 00000", passwordEncoder.encode("Admin@123"), "ADMIN");
                admin = userRepository.save(admin);
            }
            String token = "TE-ADMIN-TOKEN-" + UUID.randomUUID();
            return new AuthResponse(admin.getId(), admin.getName(), admin.getEmail(), admin.getPhone(), "ADMIN", token);
        }

        User user = userRepository.findByIdentifier(identifier)
                .orElseThrow(() -> new BadRequestException("Invalid username, email, phone or password"));

        if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
            throw new BadRequestException("Invalid username, email, phone or password");
        }

        String token = "TE-TOKEN-" + UUID.randomUUID();
        return new AuthResponse(user.getId(), user.getName(), user.getEmail(), user.getPhone(), user.getRole(), token);
    }

    public UserProfileDTO getProfile(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));
        return new UserProfileDTO(user.getId(), user.getName(), user.getEmail(), user.getPhone(), user.getRole());
    }

    public UserProfileDTO getProfileByIdentifier(String identifier) {
        User user = userRepository.findByIdentifier(identifier)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with identifier " + identifier));
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

    public List<UserProfileDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(u -> new UserProfileDTO(u.getId(), u.getName(), u.getEmail(), u.getPhone(), u.getRole()))
                .collect(Collectors.toList());
    }
}

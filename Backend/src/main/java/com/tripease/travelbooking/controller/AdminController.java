package com.tripease.travelbooking.controller;

import com.tripease.travelbooking.dto.ApiResponse;
import com.tripease.travelbooking.dto.UserProfileDTO;
import com.tripease.travelbooking.entity.Booking;
import com.tripease.travelbooking.entity.Cancellation;
import com.tripease.travelbooking.entity.TravelPackage;
import com.tripease.travelbooking.repository.BookingRepository;
import com.tripease.travelbooking.repository.CancellationRepository;
import com.tripease.travelbooking.repository.TravelPackageRepository;
import com.tripease.travelbooking.repository.UserRepository;
import com.tripease.travelbooking.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final UserRepository userRepository;
    private final BookingRepository bookingRepository;
    private final TravelPackageRepository packageRepository;
    private final CancellationRepository cancellationRepository;
    private final AuthService authService;

    public AdminController(UserRepository userRepository,
                           BookingRepository bookingRepository,
                           TravelPackageRepository packageRepository,
                           CancellationRepository cancellationRepository,
                           AuthService authService) {
        this.userRepository = userRepository;
        this.bookingRepository = bookingRepository;
        this.packageRepository = packageRepository;
        this.cancellationRepository = cancellationRepository;
        this.authService = authService;
    }

    @GetMapping("/metrics")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getMetrics() {
        long totalUsers = userRepository.count();
        long totalPackages = packageRepository.count();
        List<Booking> bookings = bookingRepository.findAll();
        long totalBookings = bookings.size();

        long upcomingBookings = bookings.stream().filter(b -> "Upcoming".equalsIgnoreCase(b.getStatus())).count();
        long completedBookings = bookings.stream().filter(b -> "Completed".equalsIgnoreCase(b.getStatus())).count();
        long cancelledBookings = bookings.stream().filter(b -> "Cancelled".equalsIgnoreCase(b.getStatus())).count();

        double totalRevenue = bookings.stream()
                .filter(b -> !"Cancelled".equalsIgnoreCase(b.getStatus()))
                .mapToDouble(b -> b.getTotalAmount() != null ? b.getTotalAmount() : 0.0)
                .sum();

        Map<String, Object> metrics = new HashMap<>();
        metrics.put("totalUsers", totalUsers);
        metrics.put("totalPackages", totalPackages);
        metrics.put("totalBookings", totalBookings);
        metrics.put("upcomingBookings", upcomingBookings);
        metrics.put("completedBookings", completedBookings);
        metrics.put("cancelledBookings", cancelledBookings);
        metrics.put("totalRevenue", totalRevenue);

        return ResponseEntity.ok(ApiResponse.ok("Metrics retrieved", metrics));
    }

    @GetMapping("/users")
    public ResponseEntity<ApiResponse<List<UserProfileDTO>>> getAllUsers() {
        return ResponseEntity.ok(ApiResponse.ok("Users retrieved", authService.getAllUsers()));
    }

    @GetMapping("/bookings")
    public ResponseEntity<ApiResponse<List<Booking>>> getAllBookings() {
        return ResponseEntity.ok(ApiResponse.ok("Bookings retrieved", bookingRepository.findAll()));
    }

    @GetMapping("/cancellations")
    public ResponseEntity<ApiResponse<List<Cancellation>>> getCancellations() {
        return ResponseEntity.ok(ApiResponse.ok("Cancellations retrieved", cancellationRepository.findAll()));
    }
}


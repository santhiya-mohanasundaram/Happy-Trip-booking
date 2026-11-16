package com.tripease.travelbooking.controller;

import com.tripease.travelbooking.dto.ApiResponse;
import com.tripease.travelbooking.dto.BookingRequest;
import com.tripease.travelbooking.dto.BookingStatusUpdateDTO;
import com.tripease.travelbooking.entity.Booking;
import com.tripease.travelbooking.entity.Payment;
import com.tripease.travelbooking.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Booking>> createBooking(@Valid @RequestBody BookingRequest request) {
        Booking created = bookingService.createBooking(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Booking confirmed successfully with ID: " + created.getId(), created));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Booking>>> getAllBookings() {
        List<Booking> list = bookingService.getAllBookings();
        return ResponseEntity.ok(ApiResponse.ok("Bookings retrieved successfully", list));
    }

    @GetMapping("/user")
    public ResponseEntity<ApiResponse<List<Booking>>> getBookingsByUser(@RequestParam String email) {
        List<Booking> list = bookingService.getBookingsByEmail(email);
        return ResponseEntity.ok(ApiResponse.ok("User bookings retrieved", list));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Booking>> getBookingById(@PathVariable String id) {
        Booking booking = bookingService.getBookingById(id);
        return ResponseEntity.ok(ApiResponse.ok("Booking retrieved", booking));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<ApiResponse<Booking>> updateStatus(@PathVariable String id,
                                                             @Valid @RequestBody BookingStatusUpdateDTO dto) {
        Booking updated = bookingService.updateBookingStatus(id, dto.getStatus());
        return ResponseEntity.ok(ApiResponse.ok("Booking status updated to " + dto.getStatus(), updated));
    }

    @PostMapping("/{id}/cancel")
    public ResponseEntity<ApiResponse<Booking>> cancelBooking(@PathVariable String id) {
        Booking cancelled = bookingService.cancelBooking(id);
        return ResponseEntity.ok(ApiResponse.ok("Booking cancelled successfully", cancelled));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteBooking(@PathVariable String id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.ok(ApiResponse.ok("Booking deleted successfully", null));
    }

    @GetMapping("/{id}/payments")
    public ResponseEntity<ApiResponse<List<Payment>>> getPayments(@PathVariable String id) {
        List<Payment> list = bookingService.getPaymentsForBooking(id);
        return ResponseEntity.ok(ApiResponse.ok("Payment records retrieved", list));
    }
}


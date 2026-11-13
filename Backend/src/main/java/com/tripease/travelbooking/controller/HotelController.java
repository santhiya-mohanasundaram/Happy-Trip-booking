package com.tripease.travelbooking.controller;

import com.tripease.travelbooking.dto.ApiResponse;
import com.tripease.travelbooking.entity.Hotel;
import com.tripease.travelbooking.service.HotelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {

    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Hotel>>> getAllHotels() {
        List<Hotel> list = hotelService.getAllHotels();
        return ResponseEntity.ok(ApiResponse.ok("Hotels retrieved successfully", list));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Hotel>> getHotelById(@PathVariable Long id) {
        Hotel hotel = hotelService.getHotelById(id);
        return ResponseEntity.ok(ApiResponse.ok("Hotel retrieved successfully", hotel));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<Hotel>>> searchHotels(@RequestParam(required = false) String q) {
        List<Hotel> list = hotelService.searchHotels(q);
        return ResponseEntity.ok(ApiResponse.ok("Hotels matching search query", list));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Hotel>> createHotel(@RequestBody Hotel hotel) {
        Hotel created = hotelService.createHotel(hotel);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Hotel added successfully", created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Hotel>> updateHotel(@PathVariable Long id, @RequestBody Hotel hotel) {
        Hotel updated = hotelService.updateHotel(id, hotel);
        return ResponseEntity.ok(ApiResponse.ok("Hotel updated successfully", updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteHotel(@PathVariable Long id) {
        hotelService.deleteHotel(id);
        return ResponseEntity.ok(ApiResponse.ok("Hotel deleted successfully", null));
    }
}


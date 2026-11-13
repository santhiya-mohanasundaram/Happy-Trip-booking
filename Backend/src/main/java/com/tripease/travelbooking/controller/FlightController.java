package com.tripease.travelbooking.controller;

import com.tripease.travelbooking.dto.ApiResponse;
import com.tripease.travelbooking.entity.Flight;
import com.tripease.travelbooking.service.FlightService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Flight>>> getAllFlights() {
        List<Flight> list = flightService.getAllFlights();
        return ResponseEntity.ok(ApiResponse.ok("Flights retrieved successfully", list));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Flight>> getFlightById(@PathVariable Long id) {
        Flight flight = flightService.getFlightById(id);
        return ResponseEntity.ok(ApiResponse.ok("Flight retrieved successfully", flight));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<Flight>>> searchFlights(@RequestParam(required = false) String from,
                                                                  @RequestParam(required = false) String to) {
        List<Flight> list = flightService.searchFlights(from, to);
        return ResponseEntity.ok(ApiResponse.ok("Flights matching search", list));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Flight>> createFlight(@RequestBody Flight flight) {
        Flight created = flightService.createFlight(flight);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Flight added successfully", created));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteFlight(@PathVariable Long id) {
        flightService.deleteFlight(id);
        return ResponseEntity.ok(ApiResponse.ok("Flight deleted successfully", null));
    }
}


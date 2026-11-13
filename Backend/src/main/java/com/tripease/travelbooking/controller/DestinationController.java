package com.tripease.travelbooking.controller;

import com.tripease.travelbooking.dto.ApiResponse;
import com.tripease.travelbooking.entity.Destination;
import com.tripease.travelbooking.service.DestinationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/destinations")
public class DestinationController {

    private final DestinationService destinationService;

    public DestinationController(DestinationService destinationService) {
        this.destinationService = destinationService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Destination>>> getAllDestinations() {
        List<Destination> list = destinationService.getAllDestinations();
        return ResponseEntity.ok(ApiResponse.ok("Destinations retrieved successfully", list));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Destination>> getDestinationById(@PathVariable String id) {
        Destination dest = destinationService.getDestinationById(id);
        return ResponseEntity.ok(ApiResponse.ok("Destination details retrieved", dest));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<Destination>>> searchDestinations(@RequestParam(required = false) String q) {
        List<Destination> list = destinationService.searchDestinations(q);
        return ResponseEntity.ok(ApiResponse.ok("Search results retrieved", list));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Destination>> createDestination(@RequestBody Destination destination) {
        Destination created = destinationService.createDestination(destination);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Destination created successfully", created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Destination>> updateDestination(@PathVariable String id,
                                                                      @RequestBody Destination destination) {
        Destination updated = destinationService.updateDestination(id, destination);
        return ResponseEntity.ok(ApiResponse.ok("Destination updated successfully", updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteDestination(@PathVariable String id) {
        destinationService.deleteDestination(id);
        return ResponseEntity.ok(ApiResponse.ok("Destination deleted successfully", null));
    }
}


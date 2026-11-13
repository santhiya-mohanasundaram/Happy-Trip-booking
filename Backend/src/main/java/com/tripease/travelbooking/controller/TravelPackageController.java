package com.tripease.travelbooking.controller;

import com.tripease.travelbooking.dto.ApiResponse;
import com.tripease.travelbooking.entity.TravelPackage;
import com.tripease.travelbooking.service.TravelPackageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/packages")
public class TravelPackageController {

    private final TravelPackageService packageService;

    public TravelPackageController(TravelPackageService packageService) {
        this.packageService = packageService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<TravelPackage>>> getAllPackages() {
        List<TravelPackage> list = packageService.getAllPackages();
        return ResponseEntity.ok(ApiResponse.ok("Packages retrieved successfully", list));
    }

    @GetMapping("/featured")
    public ResponseEntity<ApiResponse<List<TravelPackage>>> getFeaturedPackages() {
        List<TravelPackage> list = packageService.getFeaturedPackages();
        return ResponseEntity.ok(ApiResponse.ok("Featured packages retrieved", list));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TravelPackage>> getPackageById(@PathVariable String id) {
        TravelPackage pkg = packageService.getPackageById(id);
        return ResponseEntity.ok(ApiResponse.ok("Package retrieved successfully", pkg));
    }

    @GetMapping("/filter")
    public ResponseEntity<ApiResponse<List<TravelPackage>>> filterPackages(@RequestParam(required = false) String category,
                                                                          @RequestParam(required = false) String q) {
        List<TravelPackage> list = packageService.searchAndFilter(category, q);
        return ResponseEntity.ok(ApiResponse.ok("Filtered packages retrieved", list));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<TravelPackage>> createPackage(@RequestBody TravelPackage pkg) {
        TravelPackage created = packageService.createPackage(pkg);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Package created successfully", created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<TravelPackage>> updatePackage(@PathVariable String id,
                                                                   @RequestBody TravelPackage pkg) {
        TravelPackage updated = packageService.updatePackage(id, pkg);
        return ResponseEntity.ok(ApiResponse.ok("Package updated successfully", updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deletePackage(@PathVariable String id) {
        packageService.deletePackage(id);
        return ResponseEntity.ok(ApiResponse.ok("Package deleted successfully", null));
    }
}


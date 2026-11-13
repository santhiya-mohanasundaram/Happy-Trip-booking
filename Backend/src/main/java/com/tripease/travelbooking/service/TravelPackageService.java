package com.tripease.travelbooking.service;

import com.tripease.travelbooking.entity.TravelPackage;
import com.tripease.travelbooking.exception.ResourceNotFoundException;
import com.tripease.travelbooking.repository.TravelPackageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TravelPackageService {

    private final TravelPackageRepository packageRepository;

    public TravelPackageService(TravelPackageRepository packageRepository) {
        this.packageRepository = packageRepository;
    }

    public List<TravelPackage> getAllPackages() {
        return packageRepository.findAll();
    }

    public TravelPackage getPackageById(String id) {
        return packageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Package not found with id: " + id));
    }

    public List<TravelPackage> getFeaturedPackages() {
        return packageRepository.findByFeaturedTrue();
    }

    public List<TravelPackage> searchAndFilter(String category, String query) {
        if (category != null && !category.isBlank()) {
            return packageRepository.findByCategoryIgnoreCase(category);
        }
        if (query != null && !query.isBlank()) {
            return packageRepository.findByTitleContainingIgnoreCaseOrDestinationContainingIgnoreCase(query, query);
        }
        return packageRepository.findAll();
    }

    public TravelPackage createPackage(TravelPackage pkg) {
        if (pkg.getId() == null || pkg.getId().isBlank()) {
            pkg.setId("pkg-" + System.currentTimeMillis());
        }
        if (pkg.getRating() == null) pkg.setRating(4.8);
        if (pkg.getReviewsCount() == null) pkg.setReviewsCount(1);
        if (pkg.getOriginalPrice() == null && pkg.getPrice() != null) {
            pkg.setOriginalPrice(Math.round(pkg.getPrice() * 1.3 * 100.0) / 100.0);
        }
        return packageRepository.save(pkg);
    }

    public TravelPackage updatePackage(String id, TravelPackage updated) {
        TravelPackage existing = getPackageById(id);
        existing.setTitle(updated.getTitle());
        existing.setDestination(updated.getDestination());
        existing.setRegion(updated.getRegion());
        existing.setCategory(updated.getCategory());
        existing.setDuration(updated.getDuration());
        existing.setDaysCount(updated.getDaysCount());
        existing.setNightsCount(updated.getNightsCount());
        existing.setPrice(updated.getPrice());
        existing.setOriginalPrice(updated.getOriginalPrice());
        existing.setRating(updated.getRating());
        existing.setReviewsCount(updated.getReviewsCount());
        existing.setBadge(updated.getBadge());
        existing.setFeatured(updated.getFeatured());
        existing.setImage(updated.getImage());
        existing.setOverview(updated.getOverview());
        if (updated.getGalleryJson() != null) existing.setGalleryJson(updated.getGalleryJson());
        if (updated.getHighlightsJson() != null) existing.setHighlightsJson(updated.getHighlightsJson());
        if (updated.getInclusionsJson() != null) existing.setInclusionsJson(updated.getInclusionsJson());
        if (updated.getExclusionsJson() != null) existing.setExclusionsJson(updated.getExclusionsJson());
        if (updated.getStaysJson() != null) existing.setStaysJson(updated.getStaysJson());
        if (updated.getItineraryJson() != null) existing.setItineraryJson(updated.getItineraryJson());
        return packageRepository.save(existing);
    }

    public void deletePackage(String id) {
        TravelPackage pkg = getPackageById(id);
        packageRepository.delete(pkg);
    }
}


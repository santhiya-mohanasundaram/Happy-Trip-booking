package com.tripease.travelbooking.service;

import com.tripease.travelbooking.dto.LikeResponse;
import com.tripease.travelbooking.entity.PackageLike;
import com.tripease.travelbooking.entity.TravelPackage;
import com.tripease.travelbooking.exception.ResourceNotFoundException;
import com.tripease.travelbooking.repository.PackageLikeRepository;
import com.tripease.travelbooking.repository.TravelPackageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TravelPackageService {

    private final TravelPackageRepository packageRepository;
    private final PackageLikeRepository packageLikeRepository;

    public TravelPackageService(TravelPackageRepository packageRepository, PackageLikeRepository packageLikeRepository) {
        this.packageRepository = packageRepository;
        this.packageLikeRepository = packageLikeRepository;
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
        if (pkg.getLikesCount() == null) pkg.setLikesCount(2);
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
        if (updated.getFoodDetails() != null) existing.setFoodDetails(updated.getFoodDetails());
        if (updated.getTransportation() != null) existing.setTransportation(updated.getTransportation());
        if (updated.getPlacesCovered() != null) existing.setPlacesCovered(updated.getPlacesCovered());
        if (updated.getTermsAndConditions() != null) existing.setTermsAndConditions(updated.getTermsAndConditions());
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

    @Transactional
    public LikeResponse toggleLike(String packageId, String userIdentifier) {
        TravelPackage pkg = getPackageById(packageId);
        String idStr = (userIdentifier == null || userIdentifier.isBlank()) ? "anonymous" : userIdentifier.trim();

        Optional<PackageLike> existingLike = packageLikeRepository.findByPackageIdAndUserIdentifier(packageId, idStr);
        boolean liked;
        if (existingLike.isPresent()) {
            packageLikeRepository.delete(existingLike.get());
            int current = pkg.getLikesCount() != null ? pkg.getLikesCount() : 2;
            pkg.setLikesCount(Math.max(0, current - 1));
            liked = false;
        } else {
            packageLikeRepository.save(new PackageLike(packageId, idStr));
            int current = pkg.getLikesCount() != null ? pkg.getLikesCount() : 2;
            pkg.setLikesCount(current + 1);
            liked = true;
        }
        packageRepository.save(pkg);
        return new LikeResponse(packageId, liked, pkg.getLikesCount());
    }

    public boolean hasUserLiked(String packageId, String userIdentifier) {
        if (userIdentifier == null || userIdentifier.isBlank()) return false;
        return packageLikeRepository.existsByPackageIdAndUserIdentifier(packageId, userIdentifier.trim());
    }
}

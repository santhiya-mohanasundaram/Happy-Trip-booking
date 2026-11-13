package com.tripease.travelbooking.repository;

import com.tripease.travelbooking.entity.TravelPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TravelPackageRepository extends JpaRepository<TravelPackage, String> {
    List<TravelPackage> findByFeaturedTrue();
    List<TravelPackage> findByCategoryIgnoreCase(String category);
    List<TravelPackage> findByTitleContainingIgnoreCaseOrDestinationContainingIgnoreCase(String title, String destination);
}


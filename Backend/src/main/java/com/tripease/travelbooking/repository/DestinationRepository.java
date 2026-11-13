package com.tripease.travelbooking.repository;

import com.tripease.travelbooking.entity.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, String> {
    List<Destination> findByNameContainingIgnoreCaseOrCountryContainingIgnoreCase(String name, String country);
    List<Destination> findByRegionIgnoreCase(String region);
}

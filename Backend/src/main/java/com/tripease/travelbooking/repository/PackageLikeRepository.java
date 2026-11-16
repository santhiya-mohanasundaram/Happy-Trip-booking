package com.tripease.travelbooking.repository;

import com.tripease.travelbooking.entity.PackageLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PackageLikeRepository extends JpaRepository<PackageLike, Long> {
    Optional<PackageLike> findByPackageIdAndUserIdentifier(String packageId, String userIdentifier);
    boolean existsByPackageIdAndUserIdentifier(String packageId, String userIdentifier);
    long countByPackageId(String packageId);
}

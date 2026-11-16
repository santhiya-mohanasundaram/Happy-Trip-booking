package com.tripease.travelbooking.repository;

import com.tripease.travelbooking.entity.Cancellation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CancellationRepository extends JpaRepository<Cancellation, Long> {
    Optional<Cancellation> findByBookingId(String bookingId);
    List<Cancellation> findAllByOrderByCancelledAtDesc();
}

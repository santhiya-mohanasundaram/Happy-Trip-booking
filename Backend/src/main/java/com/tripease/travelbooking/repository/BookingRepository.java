package com.tripease.travelbooking.repository;

import com.tripease.travelbooking.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, String> {
    List<Booking> findByGuestEmailOrderByCreatedAtDesc(String guestEmail);
    List<Booking> findByStatusOrderByCreatedAtDesc(String status);
    List<Booking> findAllByOrderByCreatedAtDesc();
}


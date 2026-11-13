package com.tripease.travelbooking.repository;

import com.tripease.travelbooking.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findByDepartureCityIgnoreCaseAndArrivalCityIgnoreCase(String departureCity, String arrivalCity);
    List<Flight> findByArrivalCityIgnoreCase(String arrivalCity);
}

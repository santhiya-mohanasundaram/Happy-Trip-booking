package com.tripease.travelbooking.service;

import com.tripease.travelbooking.entity.Flight;
import com.tripease.travelbooking.exception.ResourceNotFoundException;
import com.tripease.travelbooking.repository.FlightRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightService {

    private final FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Flight getFlightById(Long id) {
        return flightRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Flight not found with id: " + id));
    }

    public List<Flight> searchFlights(String from, String to) {
        if (from != null && !from.isBlank() && to != null && !to.isBlank()) {
            return flightRepository.findByDepartureCityIgnoreCaseAndArrivalCityIgnoreCase(from, to);
        } else if (to != null && !to.isBlank()) {
            return flightRepository.findByArrivalCityIgnoreCase(to);
        }
        return flightRepository.findAll();
    }

    public Flight createFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    public void deleteFlight(Long id) {
        Flight flight = getFlightById(id);
        flightRepository.delete(flight);
    }
}


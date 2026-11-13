package com.tripease.travelbooking.service;

import com.tripease.travelbooking.entity.Destination;
import com.tripease.travelbooking.exception.ResourceNotFoundException;
import com.tripease.travelbooking.repository.DestinationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DestinationService {

    private final DestinationRepository destinationRepository;

    public DestinationService(DestinationRepository destinationRepository) {
        this.destinationRepository = destinationRepository;
    }

    public List<Destination> getAllDestinations() {
        return destinationRepository.findAll();
    }

    public Destination getDestinationById(String id) {
        return destinationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Destination not found with id: " + id));
    }

    public List<Destination> searchDestinations(String query) {
        if (query == null || query.isBlank()) {
            return destinationRepository.findAll();
        }
        return destinationRepository.findByNameContainingIgnoreCaseOrCountryContainingIgnoreCase(query, query);
    }

    public Destination createDestination(Destination destination) {
        if (destination.getId() == null || destination.getId().isBlank()) {
            destination.setId(destination.getName().toLowerCase().replaceAll("[^a-z0-9]", "-"));
        }
        return destinationRepository.save(destination);
    }

    public Destination updateDestination(String id, Destination updated) {
        Destination existing = getDestinationById(id);
        existing.setName(updated.getName());
        existing.setCountry(updated.getCountry());
        existing.setRegion(updated.getRegion());
        existing.setTag(updated.getTag());
        existing.setVibe(updated.getVibe());
        existing.setPrice(updated.getPrice());
        existing.setToursCount(updated.getToursCount());
        existing.setImage(updated.getImage());
        existing.setDescription(updated.getDescription());
        existing.setBestSeason(updated.getBestSeason());
        existing.setAvgTemp(updated.getAvgTemp());
        return destinationRepository.save(existing);
    }

    public void deleteDestination(String id) {
        Destination destination = getDestinationById(id);
        destinationRepository.delete(destination);
    }
}


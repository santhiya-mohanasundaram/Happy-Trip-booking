package com.tripease.travelbooking.service;

import com.tripease.travelbooking.entity.Hotel;
import com.tripease.travelbooking.exception.ResourceNotFoundException;
import com.tripease.travelbooking.repository.HotelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {

    private final HotelRepository hotelRepository;

    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    public Hotel getHotelById(Long id) {
        return hotelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with id: " + id));
    }

    public List<Hotel> searchHotels(String query) {
        if (query == null || query.isBlank()) {
            return hotelRepository.findAll();
        }
        return hotelRepository.findByDestinationNameContainingIgnoreCaseOrCityContainingIgnoreCase(query, query);
    }

    public Hotel createHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    public Hotel updateHotel(Long id, Hotel updated) {
        Hotel existing = getHotelById(id);
        existing.setName(updated.getName());
        existing.setDestinationName(updated.getDestinationName());
        existing.setCity(updated.getCity());
        existing.setStarRating(updated.getStarRating());
        existing.setPricePerNight(updated.getPricePerNight());
        existing.setImage(updated.getImage());
        existing.setRoomType(updated.getRoomType());
        existing.setAmenities(updated.getAmenities());
        existing.setAvailable(updated.getAvailable());
        return hotelRepository.save(existing);
    }

    public void deleteHotel(Long id) {
        Hotel hotel = getHotelById(id);
        hotelRepository.delete(hotel);
    }
}


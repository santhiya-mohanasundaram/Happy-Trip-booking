package com.tripease.travelbooking.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "hotels")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(length = 100)
    private String destinationName;

    @Column(length = 100)
    private String city;

    private Double starRating;

    private Double pricePerNight;

    @Column(length = 1000)
    private String image;

    @Column(length = 80)
    private String roomType = "Standard";

    @Column(columnDefinition = "TEXT")
    private String amenities;

    private Boolean available = true;

    public Hotel() {}

    public Hotel(String name, String destinationName, String city, Double starRating,
                 Double pricePerNight, String image, String roomType, String amenities, Boolean available) {
        this.name = name;
        this.destinationName = destinationName;
        this.city = city;
        this.starRating = starRating;
        this.pricePerNight = pricePerNight;
        this.image = image;
        this.roomType = roomType != null ? roomType : "Standard";
        this.amenities = amenities;
        this.available = available != null ? available : true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Double getStarRating() {
        return starRating;
    }

    public void setStarRating(Double starRating) {
        this.starRating = starRating;
    }

    public Double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(Double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getAmenities() {
        return amenities;
    }

    public void setAmenities(String amenities) {
        this.amenities = amenities;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}

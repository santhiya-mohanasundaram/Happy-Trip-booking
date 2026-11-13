package com.tripease.travelbooking.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "travel_packages")
public class TravelPackage {

    @Id
    @Column(length = 60)
    private String id;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(nullable = false, length = 120)
    private String destination;

    @Column(length = 80)
    private String region;

    @Column(length = 80)
    private String category;

    @Column(length = 80)
    private String duration;

    private Integer daysCount;

    private Integer nightsCount;

    private Double price;

    private Double originalPrice;

    private Double rating;

    private Integer reviewsCount;

    @Column(length = 80)
    private String badge;

    private Boolean featured = false;

    @Column(length = 1000)
    private String image;

    @Column(columnDefinition = "TEXT")
    private String galleryJson;

    @Column(columnDefinition = "TEXT")
    private String overview;

    @Column(columnDefinition = "TEXT")
    private String highlightsJson;

    @Column(columnDefinition = "TEXT")
    private String inclusionsJson;

    @Column(columnDefinition = "TEXT")
    private String exclusionsJson;

    @Column(columnDefinition = "TEXT")
    private String staysJson;

    @Column(columnDefinition = "TEXT")
    private String itineraryJson;

    public TravelPackage() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Integer getDaysCount() {
        return daysCount;
    }

    public void setDaysCount(Integer daysCount) {
        this.daysCount = daysCount;
    }

    public Integer getNightsCount() {
        return nightsCount;
    }

    public void setNightsCount(Integer nightsCount) {
        this.nightsCount = nightsCount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getReviewsCount() {
        return reviewsCount;
    }

    public void setReviewsCount(Integer reviewsCount) {
        this.reviewsCount = reviewsCount;
    }

    public String getBadge() {
        return badge;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }

    public Boolean getFeatured() {
        return featured;
    }

    public void setFeatured(Boolean featured) {
        this.featured = featured;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getGalleryJson() {
        return galleryJson;
    }

    public void setGalleryJson(String galleryJson) {
        this.galleryJson = galleryJson;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getHighlightsJson() {
        return highlightsJson;
    }

    public void setHighlightsJson(String highlightsJson) {
        this.highlightsJson = highlightsJson;
    }

    public String getInclusionsJson() {
        return inclusionsJson;
    }

    public void setInclusionsJson(String inclusionsJson) {
        this.inclusionsJson = inclusionsJson;
    }

    public String getExclusionsJson() {
        return exclusionsJson;
    }

    public void setExclusionsJson(String exclusionsJson) {
        this.exclusionsJson = exclusionsJson;
    }

    public String getStaysJson() {
        return staysJson;
    }

    public void setStaysJson(String staysJson) {
        this.staysJson = staysJson;
    }

    public String getItineraryJson() {
        return itineraryJson;
    }

    public void setItineraryJson(String itineraryJson) {
        this.itineraryJson = itineraryJson;
    }
}

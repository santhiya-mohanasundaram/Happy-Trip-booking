package com.tripease.travelbooking.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "destinations")
public class Destination {

    @Id
    @Column(length = 50)
    private String id;

    @Column(nullable = false, length = 120)
    private String name;

    @Column(nullable = false, length = 80)
    private String country;

    @Column(length = 80)
    private String region;

    @Column(length = 100)
    private String tag;

    @Column(length = 80)
    private String vibe;

    private Double price;

    private Integer toursCount;

    @Column(length = 1000)
    private String image;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(length = 100)
    private String bestSeason;

    @Column(length = 60)
    private String avgTemp;

    // Detailed Guide Fields
    @Column(length = 150)
    private String location;

    @Column(columnDefinition = "TEXT")
    private String howToReach;

    @Column(length = 150)
    private String nearestAirport;

    @Column(length = 150)
    private String nearestRailway;

    @Column(columnDefinition = "TEXT")
    private String popularAttractions;

    @Column(columnDefinition = "TEXT")
    private String famousPlaces;

    @Column(columnDefinition = "TEXT")
    private String localFood;

    @Column(columnDefinition = "TEXT")
    private String accommodationOptions;

    @Column(columnDefinition = "TEXT")
    private String localTransport;

    @Column(columnDefinition = "TEXT")
    private String travelTips;

    @Column(length = 100)
    private String estimatedBudget;

    @Column(length = 100)
    private String recommendedDuration;

    @Column(columnDefinition = "TEXT")
    private String activities;

    public Destination() {}

    public Destination(String id, String name, String country, String region, String tag, String vibe,
                       Double price, Integer toursCount, String image, String description,
                       String bestSeason, String avgTemp) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.region = region;
        this.tag = tag;
        this.vibe = vibe;
        this.price = price;
        this.toursCount = toursCount;
        this.image = image;
        this.description = description;
        this.bestSeason = bestSeason;
        this.avgTemp = avgTemp;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public String getRegion() { return region; }
    public void setRegion(String region) { this.region = region; }

    public String getTag() { return tag; }
    public void setTag(String tag) { this.tag = tag; }

    public String getVibe() { return vibe; }
    public void setVibe(String vibe) { this.vibe = vibe; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public Integer getToursCount() { return toursCount; }
    public void setToursCount(Integer toursCount) { this.toursCount = toursCount; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getBestSeason() { return bestSeason; }
    public void setBestSeason(String bestSeason) { this.bestSeason = bestSeason; }

    public String getAvgTemp() { return avgTemp; }
    public void setAvgTemp(String avgTemp) { this.avgTemp = avgTemp; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getHowToReach() { return howToReach; }
    public void setHowToReach(String howToReach) { this.howToReach = howToReach; }

    public String getNearestAirport() { return nearestAirport; }
    public void setNearestAirport(String nearestAirport) { this.nearestAirport = nearestAirport; }

    public String getNearestRailway() { return nearestRailway; }
    public void setNearestRailway(String nearestRailway) { this.nearestRailway = nearestRailway; }

    public String getPopularAttractions() { return popularAttractions; }
    public void setPopularAttractions(String popularAttractions) { this.popularAttractions = popularAttractions; }

    public String getFamousPlaces() { return famousPlaces; }
    public void setFamousPlaces(String famousPlaces) { this.famousPlaces = famousPlaces; }

    public String getLocalFood() { return localFood; }
    public void setLocalFood(String localFood) { this.localFood = localFood; }

    public String getAccommodationOptions() { return accommodationOptions; }
    public void setAccommodationOptions(String accommodationOptions) { this.accommodationOptions = accommodationOptions; }

    public String getLocalTransport() { return localTransport; }
    public void setLocalTransport(String localTransport) { this.localTransport = localTransport; }

    public String getTravelTips() { return travelTips; }
    public void setTravelTips(String travelTips) { this.travelTips = travelTips; }

    public String getEstimatedBudget() { return estimatedBudget; }
    public void setEstimatedBudget(String estimatedBudget) { this.estimatedBudget = estimatedBudget; }

    public String getRecommendedDuration() { return recommendedDuration; }
    public void setRecommendedDuration(String recommendedDuration) { this.recommendedDuration = recommendedDuration; }

    public String getActivities() { return activities; }
    public void setActivities(String activities) { this.activities = activities; }
}

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getVibe() {
        return vibe;
    }

    public void setVibe(String vibe) {
        this.vibe = vibe;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getToursCount() {
        return toursCount;
    }

    public void setToursCount(Integer toursCount) {
        this.toursCount = toursCount;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBestSeason() {
        return bestSeason;
    }

    public void setBestSeason(String bestSeason) {
        this.bestSeason = bestSeason;
    }

    public String getAvgTemp() {
        return avgTemp;
    }

    public void setAvgTemp(String avgTemp) {
        this.avgTemp = avgTemp;
    }
}


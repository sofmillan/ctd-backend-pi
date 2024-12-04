package com.umbrella.dto.request;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class NewEventDto {
    private String name;
    private String city;
    private String site;
    private Integer genre;
    private Integer category;
    private List<String> dates;
    private String description;
    private List<Integer> features;
    private String policies;
    private String artist;
    private Float price;

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getGenre() {
        return genre;
    }

    public void setGenre(Integer genre) {
        this.genre = genre;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public List<String> getDates() {
        return dates;
    }

    public void setDates(List<String> dates) {
        this.dates = dates;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Integer> getFeatures() {
        return features;
    }

    public void setFeatures(List<Integer> features) {
        this.features = features;
    }

    public String getPolicies() {
        return policies;
    }

    public void setPolicies(String policies) {
        this.policies = policies;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}

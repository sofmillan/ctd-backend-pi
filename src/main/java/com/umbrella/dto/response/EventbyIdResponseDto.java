package com.umbrella.dto.response;


import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EventbyIdResponseDto {
    private Integer id;
    private String name;
    private Integer capacity;
    private String eventDate;
    private String eventTime;
    private String description;
    private String site;
    private String city;
    private String genreName;
    private String coverImageUrl;
    private List<GalleryResponseDto> gallery;
    private List<FeatureResponseDto> features;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }

    public List<GalleryResponseDto> getGallery() {
        return gallery;
    }

    public void setGallery(List<GalleryResponseDto> gallery) {
        this.gallery = gallery;
    }

    public List<FeatureResponseDto> getFeatures() {
        return features;
    }

    public void setFeatures(List<FeatureResponseDto> features) {
        this.features = features;
    }
}


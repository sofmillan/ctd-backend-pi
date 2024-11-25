package com.umbrella.dto.request;

import jakarta.persistence.Tuple;
import org.springframework.data.util.Pair;

import java.time.LocalDateTime;
import java.util.List;

public class SearchRequestDto {
    List<String> genres;
    List<LocalDateTime> dates;
    String city;
    String event;

    public List<String> getGenres() {
        return genres;
    }
    public List<LocalDateTime> getDates() {
        return dates;
    }

    public String getEvent() {
        return event;
    }

    public String getCity() {
        return city;
    }
}

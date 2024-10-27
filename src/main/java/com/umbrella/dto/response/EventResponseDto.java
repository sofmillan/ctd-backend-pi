package com.umbrella.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EventResponseDto {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("eventName")
    private String name;
}

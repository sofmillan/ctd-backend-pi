package com.umbrella.dto.response;

import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EventResponseDto {
    private Integer id;
    private String name;
    private String description;
    private String genreName;

}

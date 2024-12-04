package com.umbrella.mapper;

import com.umbrella.dto.response.EventDateResponseDto;
import com.umbrella.entity.EventDate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface EventDateMapper {
    @Mapping(source = ".", target = "eventDate", qualifiedByName = "mapLocalDateToString")
    EventDateResponseDto toDto(EventDate eventDate);


    @Named("mapLocalDateToString")
    default String mapLocalDateToString(EventDate eventDate) {
        DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        return eventDate.getEventDate() != null && eventDate.getEventTime() != null
                ? FORMATTER.format(eventDate.getEventDate().atTime(eventDate.getEventTime()))
                : null;
    }
}

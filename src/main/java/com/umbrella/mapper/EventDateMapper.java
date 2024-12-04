package com.umbrella.mapper;

import com.umbrella.dto.response.EventDateResponseDto;
import com.umbrella.entity.EventDate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface EventDateMapper {
    @Mapping(source = "eventDate", target = "eventDate", dateFormat = "yyyy-MM-dd", qualifiedByName = "mapLocalDateToString")
    EventDateResponseDto toDto(EventDate eventDate);


    @Named("mapLocalDateToString")
    default String mapLocalDateToString(LocalDate date) {
        return date != null ? date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : null;
    }
}

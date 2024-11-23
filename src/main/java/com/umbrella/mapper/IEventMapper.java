package com.umbrella.mapper;

import com.umbrella.dto.response.EventResponseDto;
import com.umbrella.dto.response.EventbyIdResponseDto;
import com.umbrella.entity.Event;
import com.umbrella.entity.Gallery;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IEventMapper {
    @Mapping(target = "genreName", source = "genre.name")
/*    @Mapping(target = "eventDate", source = "eventDate", qualifiedByName = "formatDateToDayMonth")
    @Mapping(target = "eventTime", source = "eventTime", qualifiedByName = "formatTimeToHourMinuteHrs")*/
    EventResponseDto toDto(Event event);


    @Mapping(target = "genreName", source = "genre.name")
/*    @Mapping(target = "eventDate", source = "eventDate", qualifiedByName = "formatDateToFull")
    @Mapping(target = "eventTime", source = "eventTime", qualifiedByName = "formatTimeToHourMinuteHrs")*/
    EventbyIdResponseDto toDetail(Event event);

    @Named("mapGalleryUrls")
    default Set<String> mapGalleryUrls(Set<Gallery> galleries) {
        return galleries.stream()
                .map(Gallery::getImageUrl)
                .collect(Collectors.toSet());
    }

    @Named("formatDateToDayMonth")
    default String formatDateToDayMonth(LocalDate date) {
        return date != null
                ? date.format(DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH)).toUpperCase()
                : null;
    }

    @Named("formatTimeToHourMinuteHrs")
    default String formatTimeToHourMinuteHrs(LocalTime time) {
        return time != null
                ? time.format(DateTimeFormatter.ofPattern("HH:mm")) + " hrs"
                : null;
    }

    @Named("formatDateToFull")
    default String formatDateToFull(LocalDate date) {
        return date != null
                ? date.format(DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.ENGLISH))
                : null;
    }

}

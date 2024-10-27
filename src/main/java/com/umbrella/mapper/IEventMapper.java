package com.umbrella.mapper;

import com.umbrella.dto.response.EventResponseDto;
import com.umbrella.entity.Event;
import com.umbrella.entity.Gallery;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper
public interface IEventMapper {

    IEventMapper INSTANCE = Mappers.getMapper(IEventMapper.class);

    @Mapping(target = "genreName", source = "genre.name")
    @Mapping(target = "galleryUrls", qualifiedByName = "mapGalleryUrls", source = "galleries")
    EventResponseDto toDto(Event event);

    List<EventResponseDto> toListDto(List<Event> events);

    @Named("mapGalleryUrls")
    default Set<String> mapGalleryUrls(Set<Gallery> galleries) {
        return galleries.stream()
                .map(Gallery::getImageUrl)
                .collect(Collectors.toSet());
    }

}

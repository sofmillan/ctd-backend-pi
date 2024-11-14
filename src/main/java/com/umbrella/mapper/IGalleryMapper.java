package com.umbrella.mapper;

import com.umbrella.dto.response.GalleryListResponseDto;
import com.umbrella.dto.response.GalleryResponseDto;
import com.umbrella.entity.Gallery;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IGalleryMapper {
    GalleryListResponseDto toResponseDto(Gallery gallery);
}

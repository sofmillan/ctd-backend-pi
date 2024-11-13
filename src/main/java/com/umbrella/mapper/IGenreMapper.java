package com.umbrella.mapper;

import com.umbrella.dto.response.GenreResponseDto;
import com.umbrella.entity.Genre;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IGenreMapper {
    GenreResponseDto toResponseDto(Genre genre);
}

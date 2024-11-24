package com.umbrella.mapper;

import com.umbrella.dto.response.CategoryResponseDto;
import com.umbrella.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface ICategoryMapper {
    CategoryResponseDto toResponseDto(Category gallery);
}

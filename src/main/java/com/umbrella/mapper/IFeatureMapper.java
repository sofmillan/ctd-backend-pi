package com.umbrella.mapper;

import com.umbrella.dto.response.FeatureResponseDto;
import com.umbrella.entity.Feature;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IFeatureMapper {
    FeatureResponseDto toResponse(Feature feature);
}

package com.umbrella.service;

import com.umbrella.dto.request.FeatureRequestDto;
import com.umbrella.dto.response.FeatureResponseDto;
import com.umbrella.exception.ResourceNotFoundException;

import java.util.List;

public interface IFeatureService {
    List<FeatureResponseDto> getFeatures();
    void updateFeature(Integer id,FeatureRequestDto featureRequestDto);
    void deleteFeature(Integer id) throws ResourceNotFoundException;
    FeatureResponseDto createFeature(FeatureRequestDto featureRequestDto);
}

package com.umbrella.service;

import com.umbrella.dto.response.FeatureResponseDto;

import java.util.List;

public interface IFeatureService {
    List<FeatureResponseDto> getFeatures();
}

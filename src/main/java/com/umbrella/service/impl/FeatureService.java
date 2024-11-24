package com.umbrella.service.impl;

import com.umbrella.dto.response.FeatureResponseDto;
import com.umbrella.mapper.IFeatureMapper;
import com.umbrella.repository.FeatureRepository;
import com.umbrella.service.IFeatureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeatureService implements IFeatureService {
    private final FeatureRepository featureRepository;
    private final IFeatureMapper featureMapper;
    @Override
    public List<FeatureResponseDto> getFeatures() {
        return featureRepository.findAll().stream().map(featureMapper::toResponse).toList();
    }
}

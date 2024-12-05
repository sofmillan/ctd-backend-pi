package com.umbrella.service.impl;

import com.umbrella.dto.request.FeatureRequestDto;
import com.umbrella.dto.response.FeatureResponseDto;
import com.umbrella.entity.Feature;
import com.umbrella.exception.ResourceNotFoundException;
import com.umbrella.mapper.IFeatureMapper;
import com.umbrella.repository.EventFeatureRepository;
import com.umbrella.repository.FeatureRepository;
import com.umbrella.service.IFeatureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FeatureService implements IFeatureService {
    private final FeatureRepository featureRepository;
    private final EventFeatureRepository eventFeatureRepository;
    private final IFeatureMapper featureMapper;
    @Override
    public List<FeatureResponseDto> getFeatures() {
        return featureRepository.findAll().stream().map(featureMapper::toResponse).toList();
    }
    @Override
    public void updateFeature(Integer id, FeatureRequestDto featureRequestDto){
        Optional<Feature> feature = featureRepository.findById(id);
        Feature featureUpdated = new Feature();
        if(feature.isPresent()){
            featureUpdated.setId(id);
            featureUpdated.setTitle(featureRequestDto.getTitle());
            featureUpdated.setIconCode(featureRequestDto.getIconCode());
            featureRepository.save(featureUpdated);
        }
    }
    @Override
    public void deleteFeature(Integer id) throws ResourceNotFoundException{
        Optional<Feature> feature = featureRepository.findById(id);
        if(feature.isPresent()){
            eventFeatureRepository.deleteByFeatureId(id);
            featureRepository.deleteById(id);
        } else{
            throw new ResourceNotFoundException("{\"message\": \"Feature not found\"}");
        }
    }
    @Override
    public FeatureResponseDto createFeature(FeatureRequestDto featureRequestDto){
        Feature feature = featureMapper.toEntity(featureRequestDto);
        Feature featureSaved = featureRepository.save(feature);
        return featureMapper.toResponse(featureSaved);
    }
}

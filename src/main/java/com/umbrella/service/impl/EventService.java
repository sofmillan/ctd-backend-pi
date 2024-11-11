package com.umbrella.service.impl;

import com.umbrella.dto.response.EventResponseDto;
import com.umbrella.dto.response.EventbyIdResponseDto;
import com.umbrella.entity.Event;
import com.umbrella.entity.EventFeature;
import com.umbrella.entity.Gallery;
import com.umbrella.exception.ResourceNotFoundException;
import com.umbrella.mapper.IEventMapper;
import com.umbrella.mapper.IFeatureMapper;
import com.umbrella.mapper.IGalleryMapper;
import com.umbrella.repository.EventFeatureRepository;
import com.umbrella.repository.GalleryRepository;
import com.umbrella.repository.IEventRepository;
import com.umbrella.service.IEventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventService implements IEventService {

    private final IEventRepository eventRepository;
    private final GalleryRepository galleryRepository;
    private final IEventMapper eventMapper;
    private final IGalleryMapper galleryMapper;
    private final EventFeatureRepository eventFeatureRepository;
    private final IFeatureMapper featureMapper;

    @Override
    public List<EventResponseDto> findAll(){
        List<Event> events = eventRepository.findAll();
        log.info("events {}", events);
        return events.stream().map(eventMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public EventbyIdResponseDto findById(Integer id) {
        Event event = eventRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(""));
        EventbyIdResponseDto response = eventMapper.toDetail(event);
        List<Gallery> galleries =  galleryRepository.findByEventId(id);
        response.setGallery(galleries.stream().map(galleryMapper::toResponseDto).collect(Collectors.toList()));
        List<EventFeature> eventFeatures = eventFeatureRepository.findByEventId(id);
        response.setFeatures(eventFeatures.stream().map(e ->featureMapper.toResponse( e.getFeature())).collect(Collectors.toList()));
        return response;
    }
}

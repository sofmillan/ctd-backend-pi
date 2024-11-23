package com.umbrella.service.impl;

import com.umbrella.aws.FileStore;
import com.umbrella.dto.request.NewEventDto;
import com.umbrella.dto.response.EventResponseDto;
import com.umbrella.dto.response.EventbyIdResponseDto;
import com.umbrella.entity.*;
import com.umbrella.exception.ResourceNotFoundException;
import com.umbrella.mapper.IEventMapper;
import com.umbrella.mapper.IFeatureMapper;
import com.umbrella.mapper.IGalleryMapper;
import com.umbrella.repository.*;
import com.umbrella.service.IEventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    private final FileStore fileStore;
    private final GenreRepository genreRepository;
    private final CategoryRepository categoryRepository;
    private final FeatureRepository featureRepository;

    @Override
    public List<EventResponseDto> findAll(){
        List<Event> events = eventRepository.findAll();

        return events.stream().map(eventMapper::toDto)
        .collect(Collectors.toList());
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
    @Override
    public void deleteById(Integer id) throws ResourceNotFoundException {
        boolean exists = eventRepository.existsById(id);
        if (!exists) {
            throw new ResourceNotFoundException("{\"message\": \"Resource not found\"}");
        }
        eventRepository.deleteById(id);
    }

    @Override
    public void saveEvent(MultipartFile file, NewEventDto newEvent) {

        Event event = new Event();
        event.setName(newEvent.getName());
        event.setCity(newEvent.getCity());
        event.setDescription(newEvent.getDescription());
        event.setPolicies(newEvent.getPolicies());
        event.setSite(newEvent.getSite());
        event.setArtist(newEvent.getArtist());
        Genre genre = genreRepository.findById(newEvent.getGenre()).orElseThrow(ResourceNotFoundException::new);
        event.setGenre(genre);
        String coverImageUrl = uploadImage(file);
        event.setCoverImageUrl(coverImageUrl);
        Category category = categoryRepository.findById(newEvent.getCategory()).orElseThrow(ResourceNotFoundException::new);
        event.setCategory(category);
        Event savedEvent = eventRepository.save(event);

        List<Feature> found = newEvent.getFeatures().stream().map(id -> featureRepository.findById(id).orElseThrow(ResourceNotFoundException::new)).toList();
        found.forEach(feature -> {
            EventFeature eventFeature = new EventFeature();
            eventFeature.setFeature(feature);
            eventFeature.setEvent(savedEvent);
            eventFeatureRepository.save(eventFeature);
        });
    }


    public String uploadImage(MultipartFile file)  {
        if(file.isEmpty()){
            throw new IllegalArgumentException();
        }

        String fileName =  file.getOriginalFilename();
        String contentType = file.getContentType();
        long size =file.getSize();

        try {
            return fileStore.save( fileName, file.getInputStream(), contentType, size);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

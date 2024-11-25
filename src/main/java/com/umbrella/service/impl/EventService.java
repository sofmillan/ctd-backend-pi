package com.umbrella.service.impl;

import com.umbrella.aws.FileStore;
import com.umbrella.builder.SpecificationBuilder;
import com.umbrella.dto.request.NewEventDto;
import com.umbrella.dto.request.SearchRequestDto;
import com.umbrella.dto.response.EventResponseDto;
import com.umbrella.dto.response.EventbyIdResponseDto;
import com.umbrella.entity.*;
import com.umbrella.exception.ResourceAlreadyExistException;
import com.umbrella.exception.ResourceNotFoundException;
import com.umbrella.mapper.IEventMapper;
import com.umbrella.mapper.IFeatureMapper;
import com.umbrella.mapper.IGalleryMapper;
import com.umbrella.repository.*;
import com.umbrella.service.IEventService;
import com.umbrella.spec.JoinInIdsSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import static com.umbrella.builder.Condition.LogicalOperatorType.AND;
import static com.umbrella.builder.Condition.LogicalOperatorType.END;
import static com.umbrella.builder.Condition.OperationType.EQUAL;
import static com.umbrella.builder.Condition.OperationType.LIKE;

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
    private final EventDateRepository eventDateRepository;

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
    public void saveEvent(MultipartFile file, NewEventDto newEvent, List<MultipartFile> gallery) {
        if(eventRepository.findByName(newEvent.getName()).isPresent()){
            throw new ResourceAlreadyExistException("Event named "+newEvent.getName()+" already exists. Try a new one");
        }

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

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        newEvent.getDates().forEach(date->{
            EventDate eventDate = new EventDate();
            eventDate.setEvent(savedEvent);
            eventDate.setAvailable(true);
            String[] parts = date.split(" ");
            eventDate.setEventDate(LocalDate.parse(parts[0], dateFormatter));
            eventDate.setEventTime(LocalTime.parse(parts[1], timeFormatter));
            eventDateRepository.save(eventDate);
        });

        gallery.forEach(image ->{
            String galleryImageUrl = uploadImage(image);
            Gallery galleryImage = new Gallery();
            galleryImage.setEvent(savedEvent);
            galleryImage.setImageUrl(galleryImageUrl);
            galleryRepository.save(galleryImage);
        });
    }

    @Override
    public void editEvent(MultipartFile file, NewEventDto newEvent, List<MultipartFile> gallery, Integer idEvent) {
        Optional<Event> optionalEvent = eventRepository.findById(idEvent);
        if(optionalEvent.isEmpty()){
            throw new ResourceNotFoundException("Event with id "+idEvent+" does not exist");
        }

        Event foundEvent = optionalEvent.get();
        foundEvent.setName(newEvent.getName());
        foundEvent.setCity(newEvent.getCity());
        foundEvent.setDescription(newEvent.getDescription());
        foundEvent.setPolicies(newEvent.getPolicies());
        foundEvent.setSite(newEvent.getSite());
        foundEvent.setArtist(newEvent.getArtist());
        Genre genre = genreRepository.findById(newEvent.getGenre()).orElseThrow(ResourceNotFoundException::new);
        foundEvent.setGenre(genre);
        Category category = categoryRepository.findById(newEvent.getCategory()).orElseThrow(ResourceNotFoundException::new);
        foundEvent.setCategory(category);

        //Features
        List<EventFeature> existingFeatures = eventFeatureRepository.findByEventId(idEvent);
        existingFeatures.forEach(eventFeatureRepository::delete);

        List<Feature> found = newEvent.getFeatures().stream().map(id -> featureRepository.findById(id).orElseThrow(ResourceNotFoundException::new)).toList();
        found.forEach(feature -> {
            EventFeature eventFeature = new EventFeature();
            eventFeature.setFeature(feature);
            eventFeature.setEvent(foundEvent);
            eventFeatureRepository.save(eventFeature);
        });


        //Dates
        List<EventDate> eventDates = eventDateRepository.findByEventId(idEvent);
        eventDates.forEach(eventDateRepository::delete);

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        newEvent.getDates().forEach(date->{
            EventDate eventDate = new EventDate();
            eventDate.setEvent(foundEvent);
            eventDate.setAvailable(true);
            String[] parts = date.split(" ");
            eventDate.setEventDate(LocalDate.parse(parts[0], dateFormatter));
            eventDate.setEventTime(LocalTime.parse(parts[1], timeFormatter));
            eventDateRepository.save(eventDate);
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

    @Override
    public List<EventResponseDto> search(SearchRequestDto searchRequestDto) {
        SpecificationBuilder<Event> specBuilder = new SpecificationBuilder<>();
        Specification<Event> specEventGenre = new JoinInIdsSpecification(searchRequestDto.getGenres());
        Specification<Event> specEventAndCity = specBuilder
                .with("name",searchRequestDto.getEvent(),LIKE,AND)
                .with("city",searchRequestDto.getCity(),EQUAL,END)
                .build();
        List<Event> events = eventRepository.findAll(Specification
                .where(specEventGenre)
                .and(specEventAndCity)

        );
        return events.stream().map(eventMapper::toDto).toList();
    }

    public List<String> citiesOfEvent(){
        return eventRepository.citiesOfEvent();
    }
    public Set<String> namesEvent(String eventName){
        return eventRepository.findByNameContains(eventName).stream().map(Event::getName).collect(Collectors.toSet());
    }
}

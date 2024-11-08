package com.umbrella.service.impl;

import com.umbrella.dto.response.EventResponseDto;
import com.umbrella.dto.response.EventbyIdResponseDto;
import com.umbrella.entity.Event;
import com.umbrella.mapper.IEventMapper;
import com.umbrella.repository.IEventRepository;
import com.umbrella.service.IEventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class EventService implements IEventService {

    private final IEventRepository eventRepository;
    private final IEventMapper mapper;

    public EventService(IEventRepository eventRepository, IEventMapper mapper) {
        this.eventRepository = eventRepository;
        this.mapper = mapper;
    }

    @Override
    public List<EventResponseDto> findAll(){
        List<Event> events = eventRepository.findAll();
        log.info("events {}", events);
        return events.stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public EventbyIdResponseDto findById(Integer id) {
        Optional<Event> event = eventRepository.findById(id);
        log.info("event {}", event);


        return event.map(mapper::toEventByIdDto)
                .orElseThrow(() -> new RuntimeException("Event not found"));
    }
}

package com.umbrella.service.impl;

import com.umbrella.dto.response.EventResponseDto;
import com.umbrella.entity.Event;
import com.umbrella.mapper.IEventMapper;
import com.umbrella.repository.IEventRepository;
import com.umbrella.service.IEventService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EventService implements IEventService {
    private final IEventRepository eventRepository;
    private final IEventMapper eventMapper;

    public EventService(IEventRepository eventRepository, IEventMapper eventMapper) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
    }

    @Override
    public List<EventResponseDto> findAll(){
        List<Event> events = eventRepository.findAll();
        return eventMapper.toListDto(events);
    }
    @Override
    public EventResponseDto findById(Integer id){
        Optional<Event> event = eventRepository.findById(id);

        return event.map(eventMapper::toDto).orElse(null);
    }
}

package com.umbrella.service.impl;

import com.umbrella.dto.response.EventResponseDto;
import com.umbrella.entity.Event;
import com.umbrella.mapper.IEventMapper;
import com.umbrella.repository.IEventRepository;
import com.umbrella.service.IEventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class EventService implements IEventService {
    @Autowired
    IEventRepository eventRepository;

    @Override
    public List<EventResponseDto> findAll(){
        List<Event> events = eventRepository.findAll();
        log.info("events {}", events);
        //return events.stream().map(IEventMapper.mapper::toDto).collect(Collectors.toList());
        return IEventMapper.INSTANCE.toListDto(events);
    }
    @Override
    public EventResponseDto findById(Integer id){
        Optional<Event> event = eventRepository.findById(id);
        log.info("event {}", event);
        return event.map(IEventMapper.INSTANCE::toDto).orElse(null);
    }
}

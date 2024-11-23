package com.umbrella.service;

import com.umbrella.dto.request.NewEventDto;
import com.umbrella.dto.response.EventResponseDto;
import com.umbrella.dto.response.EventbyIdResponseDto;
import com.umbrella.entity.Event;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

public interface IEventService {
    List<EventResponseDto> findAll();
    EventbyIdResponseDto findById(Integer id);
    void deleteById(Integer id);
    List<String> citiesOfEvent();
    Set<String> namesEvent(String eventName);
    void saveEvent(MultipartFile file, NewEventDto newEvent);
}

package com.umbrella.service;

import com.umbrella.dto.request.NewEventDto;
import com.umbrella.dto.request.SearchRequestDto;
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
    void saveEvent(MultipartFile file, NewEventDto newEvent, List<MultipartFile> gallery);
    List<String> citiesOfEvent();
    Set<String> namesEvent(String eventName);
    void editEvent(MultipartFile file, NewEventDto newEvent, List<MultipartFile> gallery, Integer idEvent);
    List<EventResponseDto> search(SearchRequestDto searchRequestDto);
    List<EventResponseDto> getEventsByCategory(String category);


}

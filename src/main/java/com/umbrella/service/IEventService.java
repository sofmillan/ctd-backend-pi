package com.umbrella.service;

import com.umbrella.dto.request.NewEventDto;
import com.umbrella.dto.response.EventResponseDto;
import com.umbrella.dto.response.EventbyIdResponseDto;
import com.umbrella.entity.Event;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IEventService {
    List<EventResponseDto> findAll();
    EventbyIdResponseDto findById(Integer id);
    void deleteById(Integer id);
    void saveEvent(MultipartFile file, NewEventDto newEvent, List<MultipartFile> gallery);
}

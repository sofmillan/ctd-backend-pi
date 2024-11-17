package com.umbrella.service;

import com.umbrella.dto.response.EventResponseDto;
import com.umbrella.dto.response.EventbyIdResponseDto;
import com.umbrella.entity.Event;

import java.util.List;

public interface IEventService {
    List<EventResponseDto> findAll();
    EventbyIdResponseDto findById(Integer id);
    void deleteById(Integer id);
}

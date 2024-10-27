package com.umbrella.service;

import com.umbrella.dto.response.EventResponseDto;
import com.umbrella.entity.Event;

import java.util.List;

public interface IEventService {
    List<EventResponseDto> findAll();
    EventResponseDto findById(Integer id);
}

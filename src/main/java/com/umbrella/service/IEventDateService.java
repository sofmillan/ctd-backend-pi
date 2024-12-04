package com.umbrella.service;

import com.umbrella.dto.response.EventDateResponseDto;

import java.util.List;

public interface IEventDateService {
    void makeReservation(String date, Integer eventId, Integer userId);
    List<EventDateResponseDto> getDates(Integer eventId);
}

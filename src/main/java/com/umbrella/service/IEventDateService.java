package com.umbrella.service;

import com.umbrella.dto.response.EventDateResponseDto;
import com.umbrella.dto.response.ReservationDto;

import java.util.List;

public interface IEventDateService {
    ReservationDto makeReservation(String date, Integer eventId, Integer userId);
    List<EventDateResponseDto> getDates(Integer eventId);
}

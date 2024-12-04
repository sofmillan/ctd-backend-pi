package com.umbrella.controller;

import com.umbrella.dto.response.EventDateResponseDto;
import com.umbrella.dto.response.ReservationDto;
import com.umbrella.service.IEventDateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/dates")
public class EventDateController {

    private final IEventDateService eventDateService;

    @PostMapping("/reservation")
    public ReservationDto makeReservation(@RequestParam String date, @RequestParam Integer eventId, @RequestParam Integer userId){
        return eventDateService.makeReservation(date, eventId, userId);
    }

    @GetMapping("")
    public List<EventDateResponseDto> makeReservation(@RequestParam Integer eventId){
        return eventDateService.getDates(eventId);
    }
}

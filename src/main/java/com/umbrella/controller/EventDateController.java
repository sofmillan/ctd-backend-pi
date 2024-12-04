package com.umbrella.controller;

import com.umbrella.dto.response.EventDateResponseDto;
import com.umbrella.service.IEventDateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/dates")
public class EventDateController {

    private final IEventDateService eventDateService;

/*    @GetMapping("")
    public void makeReservation(@RequestParam String date, @RequestParam Integer eventId, @RequestParam Integer userId){
        eventDateService.makeReservation(date, eventId, userId);
    }*/

    @GetMapping("")
    public List<EventDateResponseDto> makeReservation(@RequestParam Integer eventId){
        return eventDateService.getDates(eventId);
    }
}

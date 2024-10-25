package com.umbrella.controller;

import com.umbrella.service.EventService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/event")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public void findAllEvents() {

    }

    @GetMapping("/{id}")
    public void findEventById(@PathVariable Integer eventId) {

    }
}

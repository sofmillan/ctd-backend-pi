package com.umbrella.controller;

import com.umbrella.dto.response.EventResponseDto;
import com.umbrella.dto.response.EventbyIdResponseDto;
import com.umbrella.service.IEventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {

    private final IEventService eventService;

    public EventController(IEventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public ResponseEntity<List<EventResponseDto>> findAllEvents() {
        return ResponseEntity.ok(eventService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventbyIdResponseDto> findEventById(@PathVariable Integer id) {
        EventbyIdResponseDto event = eventService.findById(id);
        return ResponseEntity.ok(event);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Integer id) {
        eventService.deleteById(id);
        return ResponseEntity.ok("{\"message\": \"event deleted\"}");
    }
}

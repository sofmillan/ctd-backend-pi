package com.umbrella.controller;

import com.umbrella.dto.request.NewEventDto;
import com.umbrella.dto.request.SearchRequestDto;
import com.umbrella.dto.response.EventResponseDto;
import com.umbrella.dto.response.EventbyIdResponseDto;
import com.umbrella.service.IEventService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

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

    @PostMapping(
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void uploadImage(@RequestParam("cover") MultipartFile file, @RequestPart("dto") NewEventDto dto, @RequestParam("gallery") List<MultipartFile> gallery){
        eventService.saveEvent(file, dto, gallery);
    }

    @PutMapping(
            path ="/{idEvent}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void editEvent(@RequestParam("cover") MultipartFile file, @RequestPart("dto") NewEventDto dto, @RequestParam("gallery") List<MultipartFile> gallery, @PathVariable Integer idEvent){
        eventService.editEvent(file, dto, gallery, idEvent);
    }

    @PostMapping("/search")
    public ResponseEntity<List<EventResponseDto>> search(@RequestBody SearchRequestDto request){
        return ResponseEntity.ok(eventService.search(request));
    }
    @GetMapping("/cities")
    public ResponseEntity<List<String>> citiesOfEvent() {
        return ResponseEntity.ok(eventService.citiesOfEvent());
    }

    @GetMapping("/names/{eventName}")
    public ResponseEntity<Set<String>> namesOfEvent(@PathVariable String eventName) {
        return ResponseEntity.ok(eventService.namesEvent(eventName));
    }
}

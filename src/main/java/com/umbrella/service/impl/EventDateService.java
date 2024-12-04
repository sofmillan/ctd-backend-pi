package com.umbrella.service.impl;

import com.umbrella.dto.response.EventDateResponseDto;
import com.umbrella.dto.response.ReservationDto;
import com.umbrella.entity.EventDate;
import com.umbrella.entity.Ticket;
import com.umbrella.entity.User;
import com.umbrella.exception.ResourceNotFoundException;
import com.umbrella.mapper.EventDateMapper;
import com.umbrella.repository.EventDateRepository;
import com.umbrella.repository.IEventRepository;
import com.umbrella.repository.TicketRepository;
import com.umbrella.repository.UserRepository;
import com.umbrella.service.IEventDateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventDateService implements IEventDateService {

    private final EventDateRepository eventDateRepository;
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final EventDateMapper eventDateMapper;

    @Override
    public ReservationDto makeReservation(String date, Integer eventId, Integer userId) {
        EventDate foundEventDate = eventDateRepository.findByEventIdAndEventDate(eventId, LocalDate.parse(date)).orElseThrow(ResourceNotFoundException::new);


        User foundUser = userRepository.findById(userId).orElseThrow(ResourceNotFoundException::new);

        Ticket newTicket = new Ticket();
        newTicket.setEvent(foundEventDate);
        newTicket.setBookingTime(LocalTime.now());
        newTicket.setBookingDate(LocalDate.now());
        newTicket.setUser(foundUser);
        newTicket.setNumber(UUID.randomUUID().toString().substring(0,5));
        Ticket savedTicket = ticketRepository.save(newTicket);
        foundEventDate.setAvailable(false);

        eventDateRepository.save(foundEventDate);
        return new ReservationDto(savedTicket.getId(), savedTicket.getNumber());
    }

    @Override
    public List<EventDateResponseDto> getDates(Integer eventId) {
        List<EventDate> eventDates = eventDateRepository.findByEventId(eventId);

        return eventDates.stream().map(eventDateMapper::toDto).toList();
    }
}

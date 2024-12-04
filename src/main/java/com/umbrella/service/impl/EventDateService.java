package com.umbrella.service.impl;

import com.umbrella.dto.response.EventDateResponseDto;
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
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventDateService implements IEventDateService {

    private final EventDateRepository eventDateRepository;
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final EventDateMapper eventDateMapper;

    @Override
    public void makeReservation(String date, Integer eventId, Integer userId) {
        EventDate foundEventDate = eventDateRepository.findByEventIdAndEventDate(eventId, LocalDate.parse(date)).orElseThrow(ResourceNotFoundException::new);

        foundEventDate.setAvailable(false);
        eventDateRepository.save(foundEventDate);
        User foundUser = userRepository.findById(userId).orElseThrow(ResourceNotFoundException::new);

        Ticket newTicket = new Ticket();
        newTicket.setEvent(foundEventDate);
        newTicket.setBookingTime(LocalDateTime.now());
        newTicket.setUser(foundUser);
        ticketRepository.save(newTicket);

    }

    @Override
    public List<EventDateResponseDto> getDates(Integer eventId) {
        List<EventDate> eventDates = eventDateRepository.findByEventId(eventId);

        return eventDates.stream().map(eventDateMapper::toDto).toList();
    }
}

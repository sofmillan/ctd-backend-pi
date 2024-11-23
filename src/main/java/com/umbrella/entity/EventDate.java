package com.umbrella.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "event_dates")
public class EventDate {
    @Id
    @NonNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;
    @Column(name = "event_date")
    private LocalDate eventDate;
    @Column(name = "event_time")
    private LocalTime eventTime;
    @Column(name = "available")
    private boolean isAvailable;
}

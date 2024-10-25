package com.umbrella.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "EVENT")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name", length = 100)
    private String name;
    @Column(name = "capacity")
    private Integer capacity;
    @Column(name = "event_date")
    private LocalDate eventDate;
    @Column(name = "event_time")
    private LocalTime eventTime;
    @Column(name = "site", length = 50)
    private String site;
    @Column(name = "city", length = 50)
    private String city;
    @Column(name = "description")
    private String description;
    @Column(name = "cover_image_url", length = 255)
    private String coverImageUrl;
    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;

}

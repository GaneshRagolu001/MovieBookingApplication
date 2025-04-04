package com.gane.MovieBookingApplication.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "`show`")
public class Show {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long showId;
   private LocalDateTime showTime;
   private Double price;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "movie_id",nullable = false)
   private Movie movie;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "theater_id",nullable = false)
    private Theater theater;

    @OneToMany(mappedBy = "show", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Booking> bookings;
}

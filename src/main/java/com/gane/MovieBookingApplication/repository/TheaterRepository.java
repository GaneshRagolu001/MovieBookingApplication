package com.gane.MovieBookingApplication.repository;

import com.gane.MovieBookingApplication.entity.Theater;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TheaterRepository extends JpaRepository<Theater,Long> {

    Optional<List<Theater>> findByTheaterLocation(String location);
}

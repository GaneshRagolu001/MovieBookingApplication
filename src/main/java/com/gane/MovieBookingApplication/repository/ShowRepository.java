package com.gane.MovieBookingApplication.repository;

import com.gane.MovieBookingApplication.entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShowRepository extends JpaRepository<Show,Long> {

    Optional<List<Show>> findByMovieId(Long movie_id);

    Optional<List<Show>> findByTheaterId(Long movie_id);
}

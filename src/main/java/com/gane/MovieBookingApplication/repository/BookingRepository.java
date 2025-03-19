package com.gane.MovieBookingApplication.repository;

import com.gane.MovieBookingApplication.entity.Booking;
import com.gane.MovieBookingApplication.entity.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking,Long> {

    List<Booking> findByUserId(Long user_id);

    List<Booking> findByShowId(Long show_id);

    List<Booking> findByBookingStatus(BookingStatus bookingStatus);
}

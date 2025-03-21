package com.gane.MovieBookingApplication.Controller;

import com.gane.MovieBookingApplication.DTO.ShowDTO;
import com.gane.MovieBookingApplication.entity.Show;
import com.gane.MovieBookingApplication.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shows")
public class ShowController {

    @Autowired
    public ShowService showService;

    @PostMapping("/createshow")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Show> createShow(@RequestBody ShowDTO showDTO){
        return ResponseEntity.ok(showService.createShow(showDTO));
    }

    @GetMapping("/getallshows")
    public ResponseEntity<List<Show>> getAllShows(){
        return ResponseEntity.ok(showService.getAllshows());
    }

    @GetMapping("/getshowsbymovie")
    public ResponseEntity<List<Show>> getShowsByMovie(@RequestParam Long movie_id){
        return ResponseEntity.ok(showService.getShowsByMovie(movie_id));
    }

    @GetMapping("/getshowsbytheater")
    public ResponseEntity<List<Show>> getShowsByTheater(@RequestParam Long theater_id){
        return ResponseEntity.ok(showService.getShowsByTheater(theater_id));
    }

    @PutMapping("/updateshow/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Show> updateShow(@PathVariable Long id,@RequestBody ShowDTO showDTO){
        return ResponseEntity.ok(showService.updateShow(id,showDTO));
    }

    @PutMapping("/deleteshow/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteShow(@PathVariable Long id){
        showService.deleteShow(id);
        return ResponseEntity.ok().build();
    }




}

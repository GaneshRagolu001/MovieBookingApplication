package com.gane.MovieBookingApplication.service;

import com.gane.MovieBookingApplication.DTO.MovieDTO;
import com.gane.MovieBookingApplication.entity.Movie;
import com.gane.MovieBookingApplication.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public Movie addMovie(MovieDTO movieDTO){
        Movie movie = new Movie();

        movie.setName(movieDTO.getName());
        movie.setGenre(movieDTO.getGenre());
        movie.setDescription(movieDTO.getDescription());
        movie.setLanguage(movieDTO.getLanguage());
        movie.setDuration(movieDTO.getDuration());
        movie.setReleaseDate(movieDTO.getReleaseDate());

       return movieRepository.save(movie);
    }

    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }

    public List<Movie> getMoviesByGenre(String genre){

        Optional<List<Movie>> movies = movieRepository.findByGenre(genre);
        if(movies.isPresent()){
            return movies.get();
        }else{
            throw new RuntimeException("No movies Found by genre : " + genre);
        }
    }

    public List<Movie> getMoviesByLanguage(String language){

        Optional<List<Movie>> movies = movieRepository.findByLanguage(language);
        if(movies.isPresent()){
            return movies.get();
        }else{
            throw new RuntimeException("No movies Found by language : " + language);
        }
    }

    public Movie getMoviesByTitle(String title){
        Optional<Movie> movie = movieRepository.findByName(title);
        if(movie.isPresent()){
            return movie.get();
        }else {
            throw new RuntimeException("No movies Found by title : " + title);
        }
    }

    public Movie updateMovie(Long id,MovieDTO movieDTO){
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new RuntimeException("No movie found by id : " + id));

        movie.setName(movieDTO.getName());
        movie.setGenre(movieDTO.getGenre());
        movie.setDescription(movieDTO.getDescription());
        movie.setLanguage(movieDTO.getLanguage());
        movie.setDuration(movieDTO.getDuration());
        movie.setReleaseDate(movie.getReleaseDate());

        return movieRepository.save(movie);
    }

    public void deleteMovie(Long id){

        movieRepository.deleteById(id);
    }





}

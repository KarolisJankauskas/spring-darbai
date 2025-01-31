package com.example.resource.controller;

import com.example.resource.model.Movie;
import com.example.resource.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

  @Autowired
  private MovieService movieService;

  @GetMapping
  public ResponseEntity<List<Movie>> getAllMovies() {
    return ResponseEntity.ok(movieService.getAllMovies());
  }

  @GetMapping("/{index}")
  public ResponseEntity<Movie> getMovieByIndex(@PathVariable int index) {
    Movie movie = movieService.getMovieByIndex(index);
    return (movie != null) ? ResponseEntity.ok(movie) : ResponseEntity.notFound().build();
  }

  @PostMapping
  public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {
    Movie savedMovie = movieService.addMovie(movie);
    return ResponseEntity.ok(savedMovie);
  }
}

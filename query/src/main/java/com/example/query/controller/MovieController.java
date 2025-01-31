package com.example.query.controller;

import com.example.query.model.Movie;
import com.example.query.service.MovieService;
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

  @GetMapping("/search")
  public ResponseEntity<List<Movie>> searchMovies(@RequestParam String title) {
    List<Movie> movies = movieService.findMoviesByTitle(title);
    return ResponseEntity.ok(movies);
  }
}

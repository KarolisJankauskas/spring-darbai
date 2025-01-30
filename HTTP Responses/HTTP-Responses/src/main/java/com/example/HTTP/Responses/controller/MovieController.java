package com.example.HTTP.Responses.controller;

import com.example.HTTP.Responses.model.Movie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/movies")
public class MovieController {

  private final List<Movie> movies = new ArrayList<>();

  @GetMapping("/{index}")
  public ResponseEntity<Movie> getMovie(@PathVariable int index) {
    if (index >= 0 && index < movies.size()) {
      return ResponseEntity.ok(movies.get(index));
    }
    return ResponseEntity.notFound().build();
  }

  @GetMapping
  public ResponseEntity<List<Movie>> getAllMovies() {
    return ResponseEntity.ok(movies);
  }

  @GetMapping("/search")
  public ResponseEntity<List<Movie>> searchMovies(@RequestParam String query) {
    List<Movie> filteredMovies = movies.stream()
            .filter(movie -> movie.getTitle().toLowerCase().contains(query.toLowerCase()))
            .toList();
    return ResponseEntity.ok(filteredMovies);
  }

  @PostMapping
  public ResponseEntity<?> addMovie(@RequestBody Movie movie) {
    if (movie.getTitle() == null || movie.getTitle().isEmpty() ||
            movie.getDirector() == null || movie.getDirector().isEmpty()) {
      return ResponseEntity.badRequest().body("Title and Director cannot be empty or null");
    }
    movies.add(movie);
    int index = movies.size() - 1;
    URI location = URI.create("/movies/" + index);
    return ResponseEntity.created(location).body(movie);
  }
}

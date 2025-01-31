package com.example.query.service;

import com.example.query.model.Movie;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {

  private final List<Movie> movies = new ArrayList<>();

  public MovieService() {
    movies.add(new Movie(1, "Shrek", "Animation", 2001));
    movies.add(new Movie(2, "Shrek 2", "Animation", 2004));
  }

  public List<Movie> findMoviesByTitle(String title) {
    return movies.stream()
            .filter(movie -> movie.getTitle().toLowerCase().contains(title.toLowerCase()))
            .collect(Collectors.toList());
  }

  public List<Movie> getAllMovies() {
    return movies;
  }
}

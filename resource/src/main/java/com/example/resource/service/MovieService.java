package com.example.resource.service;

import com.example.resource.model.Movie;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

  private final List<Movie> movies = new ArrayList<>();

  public MovieService() {
    movies.add(new Movie(1, "Terminator", "James Cameron"));
    movies.add(new Movie(2, "Titanic", "James Cameron"));
    movies.add(new Movie(3, "Terminator 2", "James Cameron"));
  }

  public List<Movie> getAllMovies() {
    return movies;
  }

  public Movie getMovieByIndex(int index) {
    if (index >= 0 && index < movies.size()) {
      return movies.get(index);
    }
    return null;
  }

  public Movie addMovie(Movie movie) {
    movies.add(movie);
    return movie;
  }
}

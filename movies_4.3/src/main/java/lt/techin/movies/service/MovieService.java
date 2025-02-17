package lt.techin.movies.service;


import lt.techin.movies.model.Actor;
import lt.techin.movies.model.Movie;
import lt.techin.movies.repository.ActorRepository;
import lt.techin.movies.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MovieService {

  private final MovieRepository movieRepository;
  @Autowired
  private ActorRepository actorRepository;

  @Autowired
  public MovieService(MovieRepository movieRepository) {
    this.movieRepository = movieRepository;
  }

  public List<Movie> findAllMovies() {
    return movieRepository.findAll();
  }

  public boolean existsMovieById(long id) {
    return movieRepository.existsById(id);
  }
  public Page<Movie> getMoviesPage(int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    return movieRepository.findAll(pageable);
  }

  public Optional<Movie> findMovieById(long id) {
    return movieRepository.findById(id);
  }

  public Movie saveMovie(Movie movie) {
    return movieRepository.save(movie);
  }

  public void deleteMovieById(long id) {
    movieRepository.deleteById(id);
  }



  public boolean existsMovieByTitle(String title) {
    return movieRepository.existsByTitle(title);
  }

  public Movie findMovieByTitle(String title) {
    return movieRepository.findByTitle(title);
  }
  public boolean existsMovieByDirector(String director) {
    return movieRepository.existsByDirector(director);
  }

  public boolean existsMovieByTitleAndDirector(String title, String director) {
    return movieRepository.existsByTitleAndDirector(title, director);
  }
  public Movie addActorsToMovie(Long movieId, Set<Long> actorIds) {
    Optional<Movie> optionalMovie = movieRepository.findById(movieId);
    if (optionalMovie.isEmpty()) {
      return null;
    }

    Movie movie = optionalMovie.get();
    Set<Actor> actors = actorIds.stream()
            .map(actorRepository::findById)
            .filter(Optional::isPresent)
            .map(Optional::get)
            .collect(Collectors.toSet());

    if (actors.isEmpty()) {
      return null;
    }

    movie.getActors().addAll(actors);
    return movieRepository.save(movie);
  }


}

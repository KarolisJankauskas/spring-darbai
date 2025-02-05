package lt.techin.movies.controller;
import lt.techin.movies.model.Movie;
import lt.techin.movies.repository.MovieRepository;
import lt.techin.movies.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class MovieController {



  private final MovieService movieService;



  @Autowired
  private MovieRepository movieRepository;


  @Autowired
  public MovieController(MovieService movieService) {
    this.movieService = movieService;
  }



  @GetMapping("/movies")
  public ResponseEntity<List<Movie>> getMovies() {
    return ResponseEntity.ok(movieService.findAllMovies());
  }


  @GetMapping("/pagination")
  public ResponseEntity<Page<Movie>> getMoviesPage(
          @RequestParam(defaultValue = "0") int page,
          @RequestParam(defaultValue = "5") int size) {

    Page<Movie> moviePage = movieService.getMoviesPage(page, size);
    return ResponseEntity.ok(moviePage);
  }





  @GetMapping("/movies/{id}")
  public ResponseEntity<Movie> getMovie(@PathVariable long id) {

    Optional<Movie> foundMovie = movieService.findMovieById(id);

    if (foundMovie.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(foundMovie.get());
  }



  @PostMapping("/save")
  public ResponseEntity<?> saveMovie(@RequestBody Movie movie) {
    if (movie.getTitle() == null || movie.getTitle().isEmpty() ||
            movie.getDirector() == null || movie.getDirector().isEmpty()) {
      return ResponseEntity.badRequest().body("Title and Director cannot be empty");
    }

    if (movieService.existsMovieByTitleAndDirector(movie.getTitle(), movie.getDirector())) {
      return ResponseEntity.badRequest().body("A movie with such title and director already exists");
    }

    return ResponseEntity.ok(movieService.saveMovie(movie));
  }



  @PutMapping("/update/{id}")
  public ResponseEntity<?> updateMovie(@PathVariable Long id, @RequestBody Movie movie) {
    Optional<Movie> existingMovie = movieRepository.findById(id);
    if (existingMovie.isEmpty()) {
      return ResponseEntity.badRequest().body("Movie not found");
    }

    if (movie.getTitle() == null || movie.getTitle().isEmpty() ||
            movie.getDirector() == null || movie.getDirector().isEmpty()) {
      return ResponseEntity.badRequest().body("Title and Director cannot be empty");
    }

    if (movieService.existsMovieByTitleAndDirector(movie.getTitle(), movie.getDirector())) {
      return ResponseEntity.badRequest().body("A movie with such title and director already exists");
    }

//    movie.setId(id);
    movieRepository.save(movie);
    return ResponseEntity.ok("Movie updated successfully");
  }

  @PutMapping("/{id}/add-actors")
  public ResponseEntity<?> addActorsToMovie(@PathVariable Long id, @RequestBody Set<Long> actorIds) {
    if (actorIds == null || actorIds.isEmpty()) {
      return ResponseEntity.badRequest().body("Actor list cannot be empty");
    }

    Movie updatedMovie = movieService.addActorsToMovie(id, actorIds);
    return updatedMovie != null ? ResponseEntity.ok(updatedMovie)
            : ResponseEntity.badRequest().body("Movie not found or no valid actors");
  }


  @DeleteMapping("/movies/{id}")
  public ResponseEntity<Void> deleteMovie(@PathVariable long id) {
    if (!movieService.existsMovieById(id)) {
      return ResponseEntity.notFound().build();
    }

    movieService.deleteMovieById(id);
    return ResponseEntity.noContent().build();
  }


  @GetMapping("/movies/search")
  public ResponseEntity<Movie> getMovieByTitle(@RequestParam String title) {

    for (Movie movie : movieService.findAllMovies()) {
      if (!movieService.existsMovieByTitle(title)) {
        return ResponseEntity.notFound().build();
      }
    }

    return ResponseEntity.ok(movieService.findMovieByTitle(title));
  }
}


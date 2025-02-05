package lt.techin.movies.repository;

import lt.techin.movies.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {

  boolean existsByTitle(String title);


  boolean existsByDirector(String director);
  boolean existsByTitleAndDirector(String title, String director);

  Movie findByTitle(String title);
}

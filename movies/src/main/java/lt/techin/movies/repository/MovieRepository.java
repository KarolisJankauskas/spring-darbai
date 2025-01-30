package lt.techin.movies.repository;

import lt.techin.movies.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {

  boolean existsByTitle(String title);

  Movie findByTitle(String title);
}

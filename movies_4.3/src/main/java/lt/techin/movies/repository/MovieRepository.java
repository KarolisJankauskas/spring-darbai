package lt.techin.movies.repository;


import lt.techin.movies.model.Movie;
import org.hibernate.query.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
  boolean existsByTitleAndDirector(String title, String director);


  boolean existsByTitle(String title);

  Movie findByTitle(String title);

  boolean existsByDirector(String director);
}



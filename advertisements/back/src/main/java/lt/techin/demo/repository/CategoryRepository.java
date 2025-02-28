package lt.techin.demo.repository;

import lt.techin.demo.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CategoryRepository extends JpaRepository<Category, Long> {


  Optional<Category> findByName(String name);


  Set<Category> findAllByIdIn(Set<Long> ids);


  @Query("SELECT c FROM Category c JOIN c.ads m WHERE m.id = :movieId")
  List<Category> findByMovieId(@Param("movieId") Long movieId);


  @Query("SELECT c FROM Category c WHERE c.ads IS EMPTY")
  List<Category> findCategoriesWithoutMovies();
}
package lt.techin.demo.repository;

import lt.techin.demo.model.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdRepository extends JpaRepository<Ad, Long> {

  List<Ad> findAllByTitleContaining(String title);

  List<Ad> findAllByTitle(String title);


  @Query("select m from Ad m where m.advertiser = ?1")
  List<Ad> findAllByAdvertiser(String advertiser);

}
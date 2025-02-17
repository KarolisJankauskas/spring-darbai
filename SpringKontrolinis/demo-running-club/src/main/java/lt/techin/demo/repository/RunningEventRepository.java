package lt.techin.demo.repository;

import lt.techin.demo.model.RunningEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface RunningEventRepository extends JpaRepository<RunningEvent, Long> {


  List<RunningEvent> findAllByNameContaining(String name);

  List<RunningEvent> findAllByName(String name);


}

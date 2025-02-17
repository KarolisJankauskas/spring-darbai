package lt.techin.movies.service;



import lt.techin.movies.model.Actor;
import lt.techin.movies.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActorService {

  @Autowired
  private ActorRepository actorRepository;

  public Actor saveActor(Actor actor) {
    return actorRepository.save(actor);
  }

  public Optional<Actor> findById(Long id) {
    return actorRepository.findById(id);
  }

  public List<Actor> findAll() {
    return actorRepository.findAll();
  }
}

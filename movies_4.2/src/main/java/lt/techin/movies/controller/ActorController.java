package lt.techin.movies.controller;



import lt.techin.movies.model.Actor;
import lt.techin.movies.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/actors")
public class ActorController {

  @Autowired
  private ActorService actorService;

  @PostMapping("/save")
  public ResponseEntity<?> saveActor(@RequestBody Actor actor) {
    return ResponseEntity.ok(actorService.saveActor(actor));
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getActor(@PathVariable Long id) {
    Optional<Actor> actor = actorService.findById(id);
    if (actor.isPresent()) {
      return ResponseEntity.ok(actor.get());
    } else {
      return ResponseEntity.badRequest().body("Actor not found");
    }
  }

  @GetMapping("/all")
  public List<Actor> getAllActors() {
    return actorService.findAll();
  }
}

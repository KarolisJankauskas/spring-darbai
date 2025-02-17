package lt.techin.demo.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lt.techin.demo.dto.RunningEventMapper;
import lt.techin.demo.dto.RunningEventDTO;
import lt.techin.demo.model.RunningEvent;
import lt.techin.demo.service.RunningEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.*;

@RestController
@RequestMapping("/api")
public class RunningEventController {

  private final RunningEventService runningEventService;

  @Autowired
  public RunningEventController(RunningEventService runningEventService) {
    this.runningEventService = runningEventService;
  }

  @GetMapping("/events")
  public ResponseEntity<List<RunningEventDTO>> getRunningEvents() {
    return ResponseEntity.ok(Collections.singletonList(RunningEventMapper.toRunningEventDTO(runningEventService.findAllRunningEvents())));
  }

  @GetMapping("/events/{id}")
  public ResponseEntity<RunningEventDTO> getBook(@PathVariable @Min(1) long id) {

    Optional<RunningEvent> foundRunningEvent = runningEventService.findRunningEventById(id);

    return foundRunningEvent.map(runningEvent -> ResponseEntity.ok(RunningEventMapper.toRunningEventDTO(runningEvent))).orElseGet(() -> ResponseEntity.notFound().build());

  }

  @PostMapping("/events")
  public ResponseEntity<?> addBook(@Valid @RequestBody RunningEventDTO runningEventDTO) {


    RunningEvent savedRunningEvent = runningEventService.saveRunningEvent(RunningEventMapper.toRunningEvent(runningEventDTO));

    return ResponseEntity.created(
                    ServletUriComponentsBuilder.fromCurrentRequest()
                            .path("/{id}")
                            .buildAndExpand(savedRunningEvent.getId())
                            .toUri())
            .body(RunningEventMapper.toRunningEventDTO(savedRunningEvent));
  }

  @PutMapping("/events/{id}")
  public ResponseEntity<?> updateRunningEvent(@PathVariable long id, @RequestBody RunningEventDTO runningEventDTO) {

    if (runningEventService.existsRunningEventById(id)) {
      RunningEvent runningEventFromDb = runningEventService.findRunningEventById(id).get();


      RunningEventMapper.updateRunningEventFromDTO(runningEventFromDb, runningEventDTO);

      runningEventService.saveRunningEvent(runningEventFromDb);

      return ResponseEntity.ok(runningEventDTO);
    }

    RunningEvent savedRunningEvent = runningEventService.saveRunningEvent(RunningEventMapper.toRunningEvent(runningEventDTO));

    return ResponseEntity.created(
                    ServletUriComponentsBuilder.fromCurrentRequest()
                            .replacePath("/api/events/{id}")
                            .buildAndExpand(savedRunningEvent.getId())
                            .toUri())
            .body(runningEventDTO);
  }

  @DeleteMapping("/events/{id}")
  public ResponseEntity<Void> deleteRunningEvent(@PathVariable long id) {
    if (!runningEventService.existsRunningEventById(id)) {
      return ResponseEntity.notFound().build();
    }

    runningEventService.deleteRunningEventById(id);
    return ResponseEntity.noContent().build();
  }


}


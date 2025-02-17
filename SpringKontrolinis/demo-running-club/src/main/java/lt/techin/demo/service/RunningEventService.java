package lt.techin.demo.service;

import jakarta.validation.constraints.Min;
import lt.techin.demo.model.RunningEvent;
import lt.techin.demo.repository.RunningEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RunningEventService {

  private final RunningEventRepository runningEventRepository;

  @Autowired
  public RunningEventService(RunningEventRepository bookRepository) {
    this.runningEventRepository = bookRepository;
  }

  public List<RunningEvent> findAllBooks() {
    return runningEventRepository.findAll();
  }

  public boolean existsBookById(long id) {
    return runningEventRepository.existsById(id);
  }

  public Optional<RunningEvent> findBookById(long id) {
    return runningEventRepository.findById(id);
  }

  public RunningEvent saveRunningEvent(RunningEvent runningEvent) {
    return runningEventRepository.save(runningEvent);
  }

  public void deleteBookById(long id) {
    runningEventRepository.deleteById(id);
  }

  public List<RunningEvent> findAllRunningEventsByNameContaining(String name) {

    return runningEventRepository.findAllByNameContaining(name);
  }

  public List<RunningEvent> findAllRunningEventsByAuthor(String name) {
    return runningEventRepository.findAllByName(name);
  }


  public RunningEvent findAllRunningEvents() {
    return null;
  }

  public Optional<RunningEvent> findRunningEventById(@Min(1) long id) {
    return Optional.empty();
  }

  public boolean existsRunningEventById(long id) {
    return false;
  }

  public void deleteRunningEventById(long id) {
  }
}

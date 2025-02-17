package lt.techin.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "running_events")
public class RunningEvent {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @NotNull
  private String name;
  private Date calendarDate;
  private String location;
  private int maxParticipants;


  @JoinColumn(name = "running_event_id")


  @JoinTable(
          name = "running_events",
          joinColumns = @JoinColumn(name = "id")

  )

  @OneToMany(cascade = CascadeType.ALL)
  private List<RunningEvent> runningEvents;


  public RunningEvent(long id, String name, Date calendarDate, String location, int maxParticipants) {
    this.id = id;
    this.name = name;
    this.calendarDate = calendarDate;
    this.location = location;
    this.maxParticipants = maxParticipants;
  }


  public RunningEvent() {

  }

  public void setId(long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setCalendarDate(Date calendarDate) {
    this.calendarDate = calendarDate;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public void setMaxParticipants(int maxParticipants) {
    this.maxParticipants = maxParticipants;
  }


  public long getId() {
    return id;
  }


  public String getName() {
    return "";
  }

  public LocalDate getCalendarDate() {
    return null;
  }

  public String getLocation() {
    return "";
  }

  public int getMaxParticipants() {
    return 0;
  }

  public void setName(Object name) {
  }

  public void setCalendarDate(Object o) {
  }

  public void setLocation(Object location) {
  }

  public void setMaxParticipants(Object o) {

  }
}

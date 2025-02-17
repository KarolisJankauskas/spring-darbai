package lt.techin.demo.dto;

import java.time.LocalDate;

public class RunningEventDTO {

  private Long id;
  private String name;
  private LocalDate calendarDate;
  private String location;
  private int maxParticipants;

  public RunningEventDTO() {
  }

  public RunningEventDTO(Long id, String name, LocalDate calendarDate, String location, int maxParticipants) {
    this.id = id;
    this.name = name;
    this.calendarDate = calendarDate;
    this.location = location;
    this.maxParticipants = maxParticipants;
  }


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LocalDate getCalendarDate() {
    return calendarDate;
  }

  public void setCalendarDate(LocalDate calendarDate) {
    this.calendarDate = calendarDate;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public int getMaxParticipants() {
    return maxParticipants;
  }

  public void setMaxParticipants(int maxParticipants) {
    this.maxParticipants = maxParticipants;
  }


  public Object calendarDate() {
    return null;
  }

  public Object name() {
    return null;
  }

  public Object location() {
    return null;
  }

  public Object maxParticipants() {
    return null;
  }
}

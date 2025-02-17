package lt.techin.demo.dto;

import java.time.LocalDateTime;

public class RegistrationResponseDTO {

  private Long id;
  private Long userId;
  private String eventName;
  private LocalDateTime registrationDate;

  public RegistrationResponseDTO() {
  }


  public RegistrationResponseDTO(Long id, Long userId, String eventName, LocalDateTime registrationDate) {
    this.id = id;
    this.userId = userId;
    this.eventName = eventName;
    this.registrationDate = registrationDate;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getEventName() {
    return eventName;
  }

  public void setEventName(String eventName) {
    this.eventName = eventName;
  }

  public LocalDateTime getRegistrationDate() {
    return registrationDate;
  }

  public void setRegistrationDate(LocalDateTime registrationDate) {
    this.registrationDate = registrationDate;
  }


}

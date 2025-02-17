package lt.techin.demo.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "registrations")
public class Registration {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private long userId;
  private long runningEventId;
  private Date registrationDate;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
          name = "running_events",
          joinColumns = @JoinColumn(name = "running_event_id"),
          inverseJoinColumns = @JoinColumn(name = "id")
  )
  private Set<RunningEvent> runningEvent;


  public void setId(long id) {
    this.id = id;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public void setRunningEventId(long runningEventId) {
    this.runningEventId = runningEventId;
  }

  public void setRegistrationDate(Date registrationDate) {
    this.registrationDate = registrationDate;
  }

  public long getId() {
    return id;
  }

  public long getUserId() {
    return userId;
  }

  public long getRunningEventId() {
    return runningEventId;
  }

  public Date getRegistrationDate() {
    return registrationDate;
  }
}

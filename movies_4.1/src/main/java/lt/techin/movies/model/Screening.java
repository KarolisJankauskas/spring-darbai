package lt.techin.movies.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Screening {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String theater;
  private LocalDateTime dateTime;

  @ManyToOne
  @JoinColumn(name = "movie_id", nullable = false)
  private Movie movie;
}

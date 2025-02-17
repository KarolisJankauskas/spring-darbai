package lt.techin.movies.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "actors")
public class Actor {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private String nationality;
  private int age;

  @ManyToMany(mappedBy = "actors")
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  private Set<Movie> movies = new HashSet<>();
}

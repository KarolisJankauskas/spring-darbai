package lt.techin.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Objects;

@Entity
@Table(name = "ads")
public class Ad {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String title;
  private String advertiser;
  private String description;
  private String imageUrl;
  private LocalDate releaseDate;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "ad_id")
  private List<Review> reviews;

  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
  @JoinTable(
          name = "ad_categories",
          joinColumns = @JoinColumn(name = "ad_id"),
          inverseJoinColumns = @JoinColumn(name = "category_id")
  )
  private Set<Category> categories = new HashSet<>();

  public Ad() {
  }

  public Ad(String title, String advertiser, String description, String imageUrl, LocalDate releaseDate) {
    this.title = title;
    this.advertiser = advertiser;
    this.description = description;
    this.imageUrl = imageUrl;
    this.releaseDate = releaseDate;
  }


  public Ad(long id, @NotEmpty @Size(min = 2, max = 150, message = "Maximum 150 characters and at least 2") String title, @NotEmpty @Size(max = 150, message = "Maximum 150 characters") @Pattern(regexp = "^[A-Z][a-z]+$", message = "Must start with an uppercase letter and continue as lowercase") String advertiser, @NotEmpty @Size(max = 65_535) String description, @Size(max = 150) String s, @PastOrPresent LocalDate localDate, List<Review> reviews, @NotEmpty(message = "Ad must have at least one category") List<Category> categories) {
  }

  public <E> Ad(@NotEmpty @Size(min = 2, max = 150, message = "Maximum 150 characters and at least 2") String title, @NotEmpty @Size(max = 150, message = "Maximum 150 characters") @Pattern(regexp = "^[A-Z][a-z]+$", message = "Must start with an uppercase letter and continue as lowercase") String advertiser, @NotEmpty @Size(max = 65_535) String description, @Size(max = 150) String s, @PastOrPresent LocalDate localDate, List<E> of, @NotEmpty(message = "Movie must have at least one category") List<Category> categories) {

  }


  public long getId() {
    return id;
  }


  public void setId(long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getAdvertiser() {
    return advertiser;
  }

  public void setAdvertiser(String director) {
    this.advertiser = advertiser;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }


  public LocalDate getReleaseDate() {
    return releaseDate;
  }

  public void setReleaseDate(LocalDate releaseDate) {
    this.releaseDate = releaseDate;
  }

  public List<Review> getReviews() {
    return reviews;
  }

  public void setReviews(List<Review> reviews) {
    this.reviews = reviews;
  }

  public Set<Category> getCategories() {
    return categories;
  }

  public void setCategories(Set<Category> categories) {
    this.categories = categories;
  }

  public void addCategory(Category category) {
    this.categories.add(category);
    category.getAds().add(this);
  }

  public void removeCategory(Category category) {
    this.categories.remove(category);
    category.getAds().remove(this);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Ad ad = (Ad) o;
    return id == ad.id;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return "Ad{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", advertiser='" + advertiser + '\'' +
            ", description='" + description + '\'' +
            ", imageUrl='" + imageUrl + '\'' +
            ", releaseDate=" + releaseDate +
            '}';
  }
}
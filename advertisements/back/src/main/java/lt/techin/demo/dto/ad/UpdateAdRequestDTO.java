//package lt.techin.demo.dto.movie;
//
//import jakarta.validation.constraints.*;
//import lt.techin.demo.model.Category;
//import lt.techin.demo.model.Review;
//
//import java.time.LocalDate;
//import java.util.List;
//
//public record UpdateMovieRequestDTO(
//        long id,
//        @NotEmpty
//        @Size(min = 2, max = 150, message = "Maximum 150 characters and at least 2")
//        String title,
//        @NotEmpty
//        @Size(max = 150, message = "Maximum 150 characters")
//        @Pattern(regexp = "^[A-Z][a-z]+$", message = "Must start with an uppercase letter and continue as lowercase")
//        String director,
//        @NotEmpty
//        @Size(max = 65_535)
//        String description,
//        @Size(max = 150)
//        String imageUrl,
//        @Min(value = 1, message = "Duration must be at least 1 minute")
//        int duration,
//        @PastOrPresent
//        LocalDate releaseDate,
//        List<Review> reviews,
//        @NotEmpty(message = "Movie must have at least one genre")
//        List<Category> categories
//) { }

package lt.techin.demo.dto.ad;

import jakarta.validation.constraints.*;
import lt.techin.demo.model.Category;
import lt.techin.demo.model.Review;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public record UpdateAdRequestDTO(
        long id,
        @NotEmpty
        @Size(min = 2, max = 150, message = "Maximum 150 characters and at least 2")
        String title,
        @NotEmpty
        @Size(max = 150, message = "Maximum 150 characters")
        @Pattern(regexp = "^[A-Z][a-z]+$", message = "Must start with an uppercase letter and continue as lowercase")
        String advertiser,
        @NotEmpty
        @Size(max = 65_535)
        String description,
        @Size(max = 150)
        String imageUrl,
        @PastOrPresent
        LocalDate releaseDate,
        List<Review> reviews,
        @NotEmpty(message = "Movie must have at least one genre")
        List<Category> categories
) {

  public Set<Long> getCategoryIds() {
    return categories.stream()
            .map(Category::getId)
            .collect(Collectors.toSet());
  }
}
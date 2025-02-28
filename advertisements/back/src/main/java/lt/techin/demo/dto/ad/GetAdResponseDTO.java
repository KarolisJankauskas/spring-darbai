package lt.techin.demo.dto.ad;

import lt.techin.demo.model.Category;
import lt.techin.demo.model.Review;

import java.time.LocalDate;
import java.util.List;

public record GetAdResponseDTO(
        long id,
        String title,
        String advertiser,
        String description,
        String imageUrl,
        LocalDate releaseDate,
        List<Category> categories,
        List<Review> reviews
) {
}
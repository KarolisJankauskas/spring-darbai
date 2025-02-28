package lt.techin.demo.dto.ad;

import lt.techin.demo.model.Category;

import java.time.LocalDate;
import java.util.List;

public record GetPartialAdResponseDTO(
        long id,
        String title,
        String advertiser,
        String imageUrl,
        LocalDate releaseDate,
        List<Category> categories
) {
}
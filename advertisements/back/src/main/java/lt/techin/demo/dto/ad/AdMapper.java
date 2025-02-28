package lt.techin.demo.dto.ad;

import lt.techin.demo.model.Category;
import lt.techin.demo.model.Ad;

import java.util.HashSet;
import java.util.Set;

public class AdMapper {

  public static GetPartialAdResponseDTO toGetPartialAdResponseDTO(Ad ad) {
    return new GetPartialAdResponseDTO(
            ad.getId(),
            ad.getTitle(),
            ad.getAdvertiser(),
            ad.getImageUrl(),
            ad.getReleaseDate(),
            ad.getCategories().stream().toList()
    );
  }

  public static CreateAdResponseDTO toCreateAdResponseDTO(Ad ad) {
    return new CreateAdResponseDTO(
            ad.getId(),
            ad.getTitle(),
            ad.getAdvertiser(),
            ad.getDescription(),
            ad.getImageUrl(),
            ad.getReleaseDate(),
            ad.getCategories().stream().toList()
    );
  }

  public static GetAdResponseDTO toGetAdResponseDTO(Ad ad) {
    return new GetAdResponseDTO(
            ad.getId(),
            ad.getTitle(),
            ad.getAdvertiser(),
            ad.getDescription(),
            ad.getImageUrl(),
            ad.getReleaseDate(),
            ad.getCategories().stream().toList(),
            ad.getReviews()
    );
  }

  public static UpdateAdResponseDTO toUpdateAdResponseDTO(Ad ad) {
    return new UpdateAdResponseDTO(
            ad.getId(),
            ad.getTitle(),
            ad.getAdvertiser(),
            ad.getDescription(),
            ad.getImageUrl(),
            ad.getReleaseDate(),
            ad.getReviews(),
            ad.getCategories().stream().toList()
    );
  }


  public static Ad toAd(CreateAdRequestDTO createAdRequestDTO) {
    Ad ad = new Ad();
    ad.setTitle(createAdRequestDTO.title());
    ad.setAdvertiser(createAdRequestDTO.advertiser());
    ad.setDescription(createAdRequestDTO.description());
    ad.setImageUrl(createAdRequestDTO.imageUrl());
    ad.setReleaseDate(createAdRequestDTO.releaseDate());

    // Convert category IDs into a set of Category objects (Assuming this is done in Service Layer)
    Set<Category> categories = new HashSet<>();
    ad.setCategories(categories);

    return ad;
  }

  public static Ad toAd(UpdateAdRequestDTO updateAdRequestDTO) {
    Ad ad = new Ad();
    ad.setId(updateAdRequestDTO.id());
    ad.setTitle(updateAdRequestDTO.title());
    ad.setAdvertiser(updateAdRequestDTO.advertiser());
    ad.setDescription(updateAdRequestDTO.description());
    ad.setImageUrl(updateAdRequestDTO.imageUrl());
    ad.setReleaseDate(updateAdRequestDTO.releaseDate());
    ad.setReviews(updateAdRequestDTO.reviews());

    // Convert category IDs into a set of Category objects (Assuming this is done in Service Layer)
    Set<Category> categories = new HashSet<>();
    ad.setCategories(categories);

    return ad;
  }
}

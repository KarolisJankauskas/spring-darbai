package lt.techin.demo.controller;

import jakarta.validation.Valid;
import lt.techin.demo.dto.ad.*;
import lt.techin.demo.model.Ad;
import lt.techin.demo.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/ads")
public class AdController {

  private static final Logger LOGGER = Logger.getLogger(AdController.class.getName());
  private final AdService adService;

  @Autowired
  public AdController(AdService movieService) {
    this.adService = movieService;
  }

  @GetMapping
  public ResponseEntity<List<GetPartialAdResponseDTO>> getAds() {
    try {
      List<Ad> ads = adService.findAllAds();

      List<GetPartialAdResponseDTO> response = ads.stream()
              .map(AdMapper::toGetPartialAdResponseDTO)
              .toList();

      return ResponseEntity.ok(response);
    } catch (Exception ex) {
      LOGGER.log(Level.SEVERE, "Error retrieving ads", ex);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
  }

//    @GetMapping
//    public ResponseEntity<List<GetPartialMovieResponseDTO>> getMovies(
//            @RequestParam(defaultValue = "10") @Min(value = 1) int size,
//            @RequestParam(defaultValue = "1") @Min(value = 1) int page
//    ) {
//        try {
//            Pageable pageable = PageRequest.of(page - 1, size);
//            Page<Movie> moviePage = movieService.findAllMoviesPage(pageable);
//            List<GetPartialMovieResponseDTO> response = moviePage.getContent().stream()
//                    .map(MovieMapper::toGetPartialMovieResponseDTO)
//                    .toList();
//            return ResponseEntity.ok(response);
//        } catch (IllegalArgumentException ex) {
//            LOGGER.log(Level.WARNING, "Invalid pagination parameters: {0}", ex.getMessage());
//            return ResponseEntity.badRequest().body(null);
//        } catch (Exception ex) {
//            LOGGER.log(Level.SEVERE, "Error retrieving movies", ex);
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//        }
//    }

  @GetMapping("/{id}")
  public ResponseEntity<GetAdResponseDTO> getMovie(@PathVariable long id) {
    try {
      Ad ad = adService.findAdById(id)
              .orElseThrow(() -> new IllegalArgumentException("Ad not found"));
      return ResponseEntity.ok(AdMapper.toGetAdResponseDTO(ad));
    } catch (IllegalArgumentException ex) {
      LOGGER.log(Level.WARNING, "Ad not found: {0}", id);
      return ResponseEntity.notFound().build();
    } catch (Exception ex) {
      LOGGER.log(Level.SEVERE, "Error retrieving movie", ex);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
  }

  @PostMapping
  public ResponseEntity<CreateAdResponseDTO> addAd(
          @Valid @RequestBody CreateAdRequestDTO createAdRequestDTO
  ) {
    try {
      if (createAdRequestDTO.getCategoryIds() == null || createAdRequestDTO.getCategoryIds().isEmpty()) {
        return ResponseEntity.badRequest().body(null);
      }

      Ad ad = AdMapper.toAd(createAdRequestDTO);
      Set<Long> categoryIds = createAdRequestDTO.getCategoryIds();
      Ad savedAd = adService.saveAd(ad, categoryIds);

      return ResponseEntity.created(
                      ServletUriComponentsBuilder.fromCurrentRequest()
                              .path("/{id}")
                              .buildAndExpand(savedAd.getId())
                              .toUri())
              .body(AdMapper.toCreateAdResponseDTO(savedAd));
    } catch (DataAccessException ex) {
      LOGGER.log(Level.SEVERE, "Database error when saving ad", ex);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    } catch (Exception ex) {
      LOGGER.log(Level.SEVERE, "Error saving ad", ex);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<UpdateAdResponseDTO> updateAd(
          @PathVariable long id,
          @Valid @RequestBody UpdateAdRequestDTO updateAdRequestDTO
  ) {
    try {
      if (!adService.existsAdById(id)) {
        return ResponseEntity.notFound().build();
      }

      Ad adFromDb = adService.findAdById(id)
              .orElseThrow(() -> new IllegalArgumentException("Ad not found"));

      Ad updatedAd = AdMapper.toAd(updateAdRequestDTO);
      updatedAd.setId(adFromDb.getId());

      Set<Long> categoryIds = updateAdRequestDTO.getCategoryIds();
      Ad savedAd = adService.saveAd(updatedAd, categoryIds);

      return ResponseEntity.ok(AdMapper.toUpdateAdResponseDTO(savedAd));
    } catch (IllegalArgumentException ex) {
      LOGGER.log(Level.WARNING, "Invalid update request: {0}", ex.getMessage());
      return ResponseEntity.badRequest().body(null);
    } catch (Exception ex) {
      LOGGER.log(Level.SEVERE, "Error updating ad", ex);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteAd(@PathVariable long id) {
    try {
      if (!adService.existsAdById(id)) {
        return ResponseEntity.notFound().build();
      }

      adService.deleteAdById(id);
      return ResponseEntity.noContent().build();
    } catch (DataAccessException ex) {
      LOGGER.log(Level.SEVERE, "Database error when deleting ad", ex);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    } catch (Exception ex) {
      LOGGER.log(Level.SEVERE, "Error deleting ad", ex);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }
}

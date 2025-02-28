package lt.techin.demo.service;

import lt.techin.demo.model.Ad;
import lt.techin.demo.model.Category;
import lt.techin.demo.repository.AdRepository;
import lt.techin.demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AdService {

  private final AdRepository adRepository;
  private final CategoryRepository categoryRepository;

  @Autowired
  public AdService(AdRepository adRepository, CategoryRepository categoryRepository) {
    this.adRepository = adRepository;
    this.categoryRepository = categoryRepository;
  }

  public List<Ad> findAllAds() {
    return adRepository.findAll();
  }

  public boolean existsAdById(long id) {
    return adRepository.existsById(id);
  }

  public Optional<Ad> findAdById(long id) {
    return adRepository.findById(id);
  }

  public Ad saveAd(Ad ad, Set<Long> categoryIds) {
    // Fetch categories by IDs
    Set<Category> categories = categoryRepository.findAllByIdIn(categoryIds);
    ad.setCategories(categories); // Set the categories for the movie
    return adRepository.save(ad);
  }

  public void deleteAdById(long id) {
    adRepository.deleteById(id);
  }

  public List<Ad> findAllAdsByTitleContaining(String title) {
    return adRepository.findAllByTitleContaining(title);
  }

  public List<Ad> findAllAdsByAdvertiser(String advertiser) {
    return adRepository.findAllByAdvertiser(advertiser);
  }

  public Page<Ad> findAllAdsPage(Pageable pageable) {
    return adRepository.findAll(pageable);
  }
}
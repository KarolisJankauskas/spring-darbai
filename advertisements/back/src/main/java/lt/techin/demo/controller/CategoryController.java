package lt.techin.demo.controller;

import lt.techin.demo.dto.category.CategoryMapper;
import lt.techin.demo.dto.category.GetCategoryResponseDTO;
import lt.techin.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

  private final CategoryService categoryService;

  @Autowired
  public CategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @GetMapping("/categories")
  public ResponseEntity<List<GetCategoryResponseDTO>> getCategories() {
    return ResponseEntity.ok(categoryService.findAllCategories()
            .stream()
            .map(CategoryMapper::toGetCategoryResponseDTO)
            .toList()
    );
  }
}

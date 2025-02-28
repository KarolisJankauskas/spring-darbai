package lt.techin.demo.dto.category;

import lt.techin.demo.model.Category;

public class CategoryMapper {

    public static GetCategoryResponseDTO toGetCategoryResponseDTO(Category category) {
        return new GetCategoryResponseDTO(
            category.getId(),
            category.getName()
        );
    }
}

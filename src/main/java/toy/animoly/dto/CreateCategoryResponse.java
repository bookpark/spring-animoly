package toy.animoly.dto;

import lombok.Data;
import toy.animoly.entity.Category;

@Data
public class CreateCategoryResponse {
    private Category category;

    public CreateCategoryResponse(Category category) {
        this.category = category;
    }
}

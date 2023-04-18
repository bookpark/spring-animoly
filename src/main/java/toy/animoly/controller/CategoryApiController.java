package toy.animoly.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import toy.animoly.dto.CreateCategoryRequest;
import toy.animoly.dto.CreateCategoryResponse;
import toy.animoly.entity.Category;
import toy.animoly.service.CategoryService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryApiController {

    private final CategoryService categoryService;

    @PostMapping("/api/categories")
    public CreateCategoryResponse createCategory(CreateCategoryRequest request) {
        Category category = categoryService.createCategory(request.getParentName(), request.getNewName());
        return new CreateCategoryResponse(category);
    }

    @GetMapping("/api/categories")
    public List<Category> getCategories() {
        return categoryService.findCategories();
    }

    @GetMapping("/api/categories/{id}")
    public Category getCategory(@PathVariable Long id) {
        return categoryService.findCategory(id);
    }

}

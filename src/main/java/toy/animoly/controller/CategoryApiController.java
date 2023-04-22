package toy.animoly.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import toy.animoly.dto.CreateCategoryRequest;
import toy.animoly.dto.CreateCategoryResponse;
import toy.animoly.entity.Category;
import toy.animoly.service.CategoryService;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
public class CategoryApiController {

    private final CategoryService categoryService;

    @PostMapping("/api/categories")
    public CreateCategoryResponse createCategory(CreateCategoryRequest request) {
        Category category = categoryService.createCategory(request.getParentName(), request.getNewName());
        return new CreateCategoryResponse(category);
    }

    @DeleteMapping("/api/categories")
    public ResponseEntity<?> deleteCategory(String categoryName) {
        categoryService.deleteCategory(categoryName);
        return new ResponseEntity<>("category removed", HttpStatus.OK);
    }

    @GetMapping("/api/categories")
    public Set<Category> getCategories() {
        return categoryService.findCategories();
    }

    @GetMapping("/api/categories/{id}")
    public Category getCategory(@PathVariable Long id) {
        return categoryService.findCategory(id);
    }

}

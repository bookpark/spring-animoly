package toy.animoly.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import toy.animoly.entity.Category;
import toy.animoly.service.CategoryService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryApiController {

    private final CategoryService categoryService;

    @PostMapping("/api/categories/create")
    public ResponseEntity<Category> createCategory(String parent, String categoryName) {
        Category newCategory = categoryService.createCategory(parent, categoryName);
        return new ResponseEntity<>(newCategory, HttpStatus.CREATED);
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

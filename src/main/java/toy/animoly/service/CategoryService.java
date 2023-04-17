package toy.animoly.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toy.animoly.entity.Category;
import toy.animoly.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    public Category createCategory(String parent, String categoryName) {
        Category parentCategory = categoryRepository.findByName(parent).orElse(null);
        Category childCategory = new Category();
        childCategory.setName(categoryName);
        if (parentCategory != null) {
            parentCategory.addChildCategory(childCategory);
        }
        categoryRepository.save(childCategory);
        return parentCategory;
    }

    public List<Category> findCategories() {
        return categoryRepository.findByParentIsNull();
    }

    public Category findCategory(Long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow();
    }

    public Optional<Category> findCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Data
    static class CreateCategoryRequest {
        private Long parentId;
        private String name;
    }

}

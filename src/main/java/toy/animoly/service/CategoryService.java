package toy.animoly.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toy.animoly.entity.Category;
import toy.animoly.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    public Category createCategory(String parentName, String newName) {
        Category parentCategory = categoryRepository.findByName(parentName).orElse(null);
        Category newCategory = new Category();
        newCategory.setName(newName);
        if (parentCategory != null) {
            parentCategory.addChildCategory(newCategory);
            return parentCategory;
        }
        validateDuplicateCategory(newCategory);
        categoryRepository.save(newCategory);
        return newCategory;
    }

    public Set<Category> findCategories() {
        return categoryRepository.findByParentIsNull();
    }

    public Category findCategory(Long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow();
    }

    public Optional<Category> findCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }

    private void validateDuplicateCategory(Category category) {
        Optional<Category> findCategory = categoryRepository.findByName(category.getName());
        if (findCategory.isPresent()) {
            throw new IllegalStateException("이미 존재하는 카테고리입니다.");
        }
    }

}

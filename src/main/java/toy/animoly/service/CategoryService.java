package toy.animoly.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toy.animoly.entity.Category;
import toy.animoly.repository.CategoryRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    public Long createCategory(Category child) {
        Category category = Category.createCategory(child);
        return category.getId();
    }

    public List<Category> findCategories() {
        return categoryRepository.findAll();
    }

}

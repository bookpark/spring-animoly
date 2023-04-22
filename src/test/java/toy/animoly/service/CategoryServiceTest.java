package toy.animoly.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import toy.animoly.entity.Category;
import toy.animoly.repository.CategoryRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CategoryServiceTest {

    @Autowired
    CategoryService categoryService;
    @Autowired
    CategoryRepository categoryRepository;

    @Test
    void createCategory() {
        //given
        Category parent = new Category();
        parent.setName("부모");
        Category children = new Category();
        children.setName("자식");

        //when
        parent.addChildCategory(children);

        //then
        assertNull(parent.getParent());
        assertEquals(1, parent.getChildren().size());
    }

    @Test
    void deleteCategory() {
        //given
        Category parent = new Category();
        parent.setName("부모");
        categoryRepository.save(parent);
        Category children = new Category();
        children.setName("자식");
        categoryRepository.save(children);
        parent.addChildCategory(children);

        //when
        categoryService.deleteCategory(parent.getName());

        //then
        List<Category> all = categoryRepository.findAll();
        assertEquals(0, all.size());
    }
}
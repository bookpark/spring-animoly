package toy.animoly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toy.animoly.entity.Category;

import java.util.List;
import java.util.Optional;


public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByName(String name);

    List<Category> findByParentIsNull();

    Category findByParentId(Long parentCategoryId);

}

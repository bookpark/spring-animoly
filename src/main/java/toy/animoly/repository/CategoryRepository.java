package toy.animoly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toy.animoly.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}

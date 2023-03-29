package toy.animoly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toy.animoly.entity.Blog;

public interface BlogRepository extends JpaRepository<Blog, Long> {
}

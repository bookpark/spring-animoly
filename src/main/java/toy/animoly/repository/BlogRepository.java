package toy.animoly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import toy.animoly.entity.Blog;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {
}

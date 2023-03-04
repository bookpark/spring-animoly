package toy.animoly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toy.animoly.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}

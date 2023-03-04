package toy.animoly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toy.animoly.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}

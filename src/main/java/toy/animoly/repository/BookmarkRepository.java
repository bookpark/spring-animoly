package toy.animoly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toy.animoly.entity.Bookmark;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
}

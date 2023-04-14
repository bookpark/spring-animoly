package toy.animoly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toy.animoly.entity.item.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
}

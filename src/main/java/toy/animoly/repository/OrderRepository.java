package toy.animoly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toy.animoly.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}

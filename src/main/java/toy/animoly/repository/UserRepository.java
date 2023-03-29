package toy.animoly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toy.animoly.entity.User;

public interface UserRepository extends JpaRepository<User, String> {
}

package toy.animoly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import toy.animoly.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}

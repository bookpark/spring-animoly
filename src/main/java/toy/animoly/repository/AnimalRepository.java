package toy.animoly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toy.animoly.entity.Animal;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
}

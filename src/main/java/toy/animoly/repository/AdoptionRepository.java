package toy.animoly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toy.animoly.entity.Adoption;

public interface AdoptionRepository extends JpaRepository<Adoption, Long> {
}

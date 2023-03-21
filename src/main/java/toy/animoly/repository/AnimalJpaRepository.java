package toy.animoly.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import toy.animoly.entity.Animal;

import javax.persistence.EntityManager;
import java.util.List;

import static toy.animoly.entity.QAdoption.*;
import static toy.animoly.entity.QAnimal.*;
import static toy.animoly.entity.QBookmark.*;

@Repository
public class AnimalJpaRepository {
    private final JPAQueryFactory query;

    public AnimalJpaRepository(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    public List<Animal> findAll() {
        return query
                .select(animal)
                .from(animal)
                .offset(0)
                .limit(100)
                .orderBy(animal.happenDt.desc())
                .fetch();
    }
}

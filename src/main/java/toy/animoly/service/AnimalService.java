package toy.animoly.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toy.animoly.entity.Animal;
import toy.animoly.repository.AnimalJpaRepository;
import toy.animoly.repository.AnimalRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AnimalService {
    private final AnimalRepository animalRepository;
    private final AnimalJpaRepository animalJpaRepository;

    public List<Animal> getAnimals() {
        return animalJpaRepository.findAll();
    }

    public Animal getAnimal(Long id) {
        return animalRepository.findById(id).orElseThrow();
    }
}

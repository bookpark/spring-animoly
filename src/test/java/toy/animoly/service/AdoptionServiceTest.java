package toy.animoly.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import toy.animoly.entity.Adoption;
import toy.animoly.entity.AdoptionStatus;
import toy.animoly.entity.Animal;
import toy.animoly.entity.User;
import toy.animoly.repository.AdoptionRepository;
import toy.animoly.repository.AnimalRepository;
import toy.animoly.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class AdoptionServiceTest {
    @Autowired
    AdoptionService adoptionService;
    @Autowired
    AdoptionRepository adoptionRepository;
    @Autowired
    AnimalRepository animalRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    void apply() {
        //given
        User user = new User();
        user.setId("userA");
        userRepository.save(user);
        Animal animal = new Animal();
        animal.setId(1L);
        animal.setAge("10");
        animalRepository.save(animal);

        //when
        Long adoptionId = adoptionService.apply(user.getId(), animal.getId());

        //then
        Adoption getAdoption = adoptionRepository.findById(adoptionId).orElseThrow();

        assertEquals("userA", getAdoption.getUser().getId());
        assertEquals("10", getAdoption.getAnimal().getAge());
        assertEquals(AdoptionStatus.APPLIED, getAdoption.getStatus());
    }
}
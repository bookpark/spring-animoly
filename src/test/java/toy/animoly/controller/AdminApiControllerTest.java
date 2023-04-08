package toy.animoly.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import toy.animoly.entity.Animal;
import toy.animoly.entity.User;
import toy.animoly.repository.AdoptionRepository;
import toy.animoly.repository.AnimalRepository;
import toy.animoly.repository.UserRepository;
import toy.animoly.service.AdoptionService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class AdminApiControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    AdoptionRepository adoptionRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AnimalRepository animalRepository;
    @Autowired
    AdoptionService adoptionService;

    @Test
    void approveCancel() throws Exception {
        User user = new User();
        user.setId("bookpark");
        userRepository.save(user);
        Animal animal = new Animal();
        animal.setDesertionNo(1L);
        animalRepository.save(animal);
        adoptionService.apply(user.getId(), animal.getDesertionNo());
        mockMvc.perform(post("/api/admin/adoptions/1/approve-cancel"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf("\"CANCELLED\"")));
    }
}
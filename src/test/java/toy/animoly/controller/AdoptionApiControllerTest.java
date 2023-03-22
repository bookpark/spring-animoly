package toy.animoly.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;
import toy.animoly.entity.Animal;
import toy.animoly.entity.User;
import toy.animoly.repository.AnimalRepository;
import toy.animoly.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class AdoptionApiControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AnimalRepository animalRepository;

    @Test
    void apply() throws Exception {
        User user = new User();
        user.setId("bookpark");
        userRepository.save(user);
        Animal animal = new Animal();
        animal.setId(1L);
        animalRepository.save(animal);
        String requestJson = "{\"userId\":\"bookpark\", \"animalId\": \"1\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/apply")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
package toy.animoly.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import toy.animoly.entity.Animal;
import toy.animoly.entity.Member;
import toy.animoly.repository.AnimalRepository;
import toy.animoly.repository.MemberRepository;
import toy.animoly.service.AdoptionService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AdoptionApiControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private AnimalRepository animalRepository;
    @Autowired
    private AdoptionService adoptionService;

    @Test
    void apply() throws Exception {
        Member member = new Member();
        member.setId("bookpark");
        memberRepository.save(member);
        Animal animal = new Animal();
        animal.setDesertionNo(1L);
        animalRepository.save(animal);
        String requestJson = "{\"userId\":\"bookpark\", \"animalId\": \"1\"}";
        mockMvc.perform(post("/api/apply")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void requestCancel() throws Exception {
        //given
        Member member = new Member();
        member.setId("bookpark");
        memberRepository.save(member);
        Animal animal = new Animal();
        animal.setDesertionNo(1L);
        animalRepository.save(animal);
        Long id = adoptionService.apply(member.getId(), animal.getDesertionNo());

        //when
        adoptionService.requestCancel(id);

        //then
        mockMvc.perform(post("/api/adoptions/1/request-cancel"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf("\"CANCEL_REQUESTED\"")));
    }
}
package toy.animoly.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import toy.animoly.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserApiControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    UserRepository userRepository;

    @Test
    void join() throws Exception {
        final String id = "bookpark";
        final String password = "qnrlqnrl";
        final String nickname = "부기당";
        final String phoneNumber = "01062323793";
        final String city = "광명시";
        final String street = "디지털로 56";
        final String zipcode = "14242";
        mockMvc.perform(multipart("/api/join")
                        .param("id", id)
                        .param("password", password)
                        .param("nickname", nickname)
                        .param("phoneNumber", phoneNumber)
                        .param("city", city)
                        .param("street", street)
                        .param("zipcode", zipcode))
                .andExpect(status().isOk());

        assertEquals("bookpark", userRepository.findById("bookpark").orElseThrow().getId());
    }
}
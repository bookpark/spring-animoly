package toy.animoly.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import toy.animoly.entity.User;
import toy.animoly.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserServiceTest {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @Test
    void join() {
        //given
        User user = new User();
        user.setId("bookpark");

        //when
        String savedId = userService.join(user);

        //then
        assertEquals("bookpark", userRepository.findById(savedId).get().getId());

    }

    @Test
    void validateDuplicateUser() throws Exception {
        //given
        User user1 = new User();
        user1.setId("bookpark");
        User user2 = new User();
        user2.setId("bookpark");

        //when
        userService.join(user1);

        //then
        IllegalStateException thrown = assertThrows(IllegalStateException.class, () -> userService.join(user2));
        assertEquals("이미 존재하는 ID입니다.", thrown.getMessage());
    }

    @Test
    void update() {
        //given
        User user = new User();
        user.setId("bookpark");
        user.setNickname("닉네임");
        userRepository.save(user);

        //when
        userService.update(user.getId(), "수정");

        //then
        assertEquals("수정", user.getNickname());
    }
}
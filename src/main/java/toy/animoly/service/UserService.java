package toy.animoly.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toy.animoly.entity.User;
import toy.animoly.repository.UserRepository;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    /**
     * 회원가입
     */
    @Transactional
    public String join(User user) {
        validateDuplicateUser(user);
        userRepository.save(user);
        return user.getId();
    }

    /**
     * 회원 조회
     */
    public User findUser(String id) {
        return userRepository.findById(id).orElseThrow();
    }

    /**
     * 정보수정
     */
    @Transactional
    public void update(String id, String nickname, String phoneNumber) {
        User user = userRepository.findById(id).orElseThrow();
        user.setNickname(nickname);
        user.setPhoneNumber(phoneNumber);
    }

    /**
     * 중복 회원 검사
     */
    private void validateDuplicateUser(User user) {
        Optional<User> findUsers = userRepository.findById(user.getId());
        if (findUsers.isPresent()) {
            throw new IllegalStateException("이미 존재하는 ID입니다.");
        }
    }
}

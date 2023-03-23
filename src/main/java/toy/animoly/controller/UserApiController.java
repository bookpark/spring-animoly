package toy.animoly.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import toy.animoly.entity.Address;
import toy.animoly.entity.User;
import toy.animoly.service.UserService;

@RestController
@RequiredArgsConstructor
public class UserApiController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/api/join")
    public JoinUserResponse join(JoinUserRequest request) {
        User user = new User();
        user.setId(request.getId());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setNickname(request.getNickname());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setAddress(new Address(request.getCity(), request.getStreet(), request.getZipcode()));
        String id = userService.join(user);
        return new JoinUserResponse(id);
    }

    @Data
    static class JoinUserRequest {
        private String id;
        private String password;
        private String nickname;
        private String phoneNumber;
        private String city;
        private String street;
        private String zipcode;
    }

    @Data
    static class JoinUserResponse {
        private String id;

        public JoinUserResponse(String id) {
            this.id = id;
        }
    }

}

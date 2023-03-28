package toy.animoly.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import toy.animoly.entity.Address;
import toy.animoly.entity.User;
import toy.animoly.service.UserService;

@RestController
@RequiredArgsConstructor
public class UserApiController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/api/users/join")
    public CreateUserResponse join(CreateUserRequest request) {
        User user = new User();
        user.setId(request.getId());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setNickname(request.getNickname());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setAddress(new Address(request.getCity(), request.getStreet(), request.getZipcode()));
        String id = userService.join(user);
        return new CreateUserResponse(id);
    }

    @PutMapping("/api/users/{id}/update")
    public UpdateUserResponse update(@PathVariable("id") String id,
                                     UpdateUserRequest request) {
        userService.update(id, request.getNickname());
        User user = userService.findUser(id);
        return new UpdateUserResponse(user.getId(), user.getNickname());
    }

    @DeleteMapping("/api/users/{id}/delete")
    public ResponseEntity<String> delete(@PathVariable("id") String id) {
        userService.delete(id);
        return new ResponseEntity<String>("회원 탈퇴 성공", HttpStatus.OK);
    }

    @Data
    static class CreateUserRequest {
        private String id;
        private String password;
        private String nickname;
        private String phoneNumber;
        private String city;
        private String street;
        private String zipcode;
    }

    @Data
    static class CreateUserResponse {
        private String id;

        public CreateUserResponse(String id) {
            this.id = id;
        }
    }

    @Data
    static class UpdateUserRequest {
        private String nickname;
    }

    @Data
    static class UpdateUserResponse {
        private String id;
        private String nickname;

        public UpdateUserResponse(String id, String nickname) {
            this.id = id;
            this.nickname = nickname;
        }
    }

}

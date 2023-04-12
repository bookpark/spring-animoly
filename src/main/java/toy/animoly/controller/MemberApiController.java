package toy.animoly.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import toy.animoly.config.JwtProvider;
import toy.animoly.entity.Address;
import toy.animoly.entity.Member;
import toy.animoly.service.UserService;

@RestController
@RequiredArgsConstructor
public class MemberApiController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @PostMapping("/api/users/join")
    public CreateUserResponse join(CreateUserRequest request) {
        Member member = new Member();
        member.setId(request.getId());
        member.setPassword(passwordEncoder.encode(request.getPassword()));
        member.setNickname(request.getNickname());
        member.setPhoneNumber(request.getPhoneNumber());
        member.setAddress(new Address(request.getCity(), request.getStreet(), request.getZipcode()));
        String id = userService.join(member);
        return new CreateUserResponse(id);
    }

    @PostMapping("/api/users/login")
    public ResponseEntity<?> login(LoginRequest request) {
        Member member = userService.findUser(request.getId());
        if (passwordEncoder.matches(request.getPassword(), member.getPassword())) {
            String token = jwtProvider.createToken(member);
            return ResponseEntity.ok(new JwtResponse(request.getId(), token));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PutMapping("/api/users/{id}/update")
    public UpdateUserResponse update(@PathVariable("id") String id,
                                     UpdateUserRequest request) {
        userService.update(id, request.getNickname());
        Member member = userService.findUser(id);
        return new UpdateUserResponse(member.getId(), member.getNickname());
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
    static class LoginRequest {
        private String id;
        private String password;
    }

    @Data
    static class JwtResponse {
        private String id;
        private final String token;

        public JwtResponse(String id, String token) {
            this.id = id;
            this.token = token;
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

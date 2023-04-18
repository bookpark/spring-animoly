package toy.animoly.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import toy.animoly.config.JwtProvider;
import toy.animoly.dto.*;
import toy.animoly.entity.Address;
import toy.animoly.entity.Member;
import toy.animoly.service.MemberService;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @PostMapping("/api/users/join")
    public CreateUserResponse join(CreateUserRequest request) {
        Member member = new Member();
        member.setId(request.getId());
        member.setPassword(passwordEncoder.encode(request.getPassword()));
        member.setName(request.getName());
        member.setNickname(request.getNickname());
        member.setPhoneNumber(request.getPhoneNumber());
        member.setAddress(new Address(request.getAddress1(), request.getAddress2(), request.getZipcode()));
        String id = memberService.join(member);
        return new CreateUserResponse(id);
    }

    @PostMapping("/api/users/login")
    public ResponseEntity<?> login(LoginRequest request) {
        Member member = memberService.findUser(request.getId());
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
        memberService.update(id, request.getNickname());
        Member member = memberService.findUser(id);
        return new UpdateUserResponse(member.getId(), member.getNickname());
    }

    @DeleteMapping("/api/users/{id}/delete")
    public ResponseEntity<String> delete(@PathVariable("id") String id) {
        memberService.delete(id);
        return new ResponseEntity<String>("회원 탈퇴 성공", HttpStatus.OK);
    }

}

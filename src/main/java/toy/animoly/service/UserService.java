package toy.animoly.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toy.animoly.entity.Member;
import toy.animoly.repository.MemberRepository;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {
    private final MemberRepository memberRepository;

    /**
     * 회원가입
     */
    @Transactional
    public String join(Member member) {
        validateDuplicateUser(member);
        memberRepository.save(member);
        return member.getId();
    }

    /**
     * 회원 조회
     */
    public Member findUser(String id) {
        return memberRepository.findById(id).orElseThrow();
    }

    /**
     * 닉네임변경
     */
    @Transactional
    public void update(String id, String nickname) {
        Member member = memberRepository.findById(id).orElseThrow();
        member.setNickname(nickname);
    }

    /**
     * 회원탈퇴
     */
    @Transactional
    public void delete(String id) {
        Member member = memberRepository.findById(id).orElseThrow();
        memberRepository.delete(member);
    }

    /**
     * 중복 회원 검사
     */
    private void validateDuplicateUser(Member member) {
        Optional<Member> findUsers = memberRepository.findById(member.getId());
        if (findUsers.isPresent()) {
            throw new IllegalStateException("이미 존재하는 ID입니다.");
        }
    }
}

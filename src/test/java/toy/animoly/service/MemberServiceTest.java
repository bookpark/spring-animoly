package toy.animoly.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import toy.animoly.entity.Member;
import toy.animoly.repository.MemberRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    void join() {
        //given
        Member member = new Member();
        member.setId("bookpark");

        //when
        String savedId = memberService.join(member);

        //then
        assertEquals("bookpark", memberRepository.findById(savedId).get().getId());

    }

    @Test
    void validateDuplicateUser() throws Exception {
        //given
        Member member1 = new Member();
        member1.setId("bookpark");
        Member member2 = new Member();
        member2.setId("bookpark");

        //when
        memberService.join(member1);

        //then
        IllegalStateException thrown = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertEquals("이미 존재하는 ID입니다.", thrown.getMessage());
    }

    @Test
    void update() {
        //given
        Member member = new Member();
        member.setId("bookpark");
        member.setNickname("닉네임");
        memberRepository.save(member);

        //when
        memberService.update(member.getId(), "수정");

        //then
        assertEquals("수정", member.getNickname());
    }
}
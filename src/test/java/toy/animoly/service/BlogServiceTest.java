package toy.animoly.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import toy.animoly.entity.Blog;
import toy.animoly.entity.Member;
import toy.animoly.repository.BlogRepository;
import toy.animoly.repository.MemberRepository;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class BlogServiceTest {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private BlogService blogService;

    @Test
    void create() {
        //given
        Member member = new Member();
        member.setId("bookpark");
        memberRepository.save(member);
        String title = "제목";
        String content = "내용";

        //when
        Long findBlog = blogService.create(member.getId(), title, content);
        Blog blog = blogRepository.findById(findBlog).orElseThrow();

        //then
        assertEquals("제목", blog.getTitle());
        assertEquals("내용", blog.getContent());
    }

    @Test
    void update() {
        //given
        Member member = new Member();
        member.setId("bookpark");
        memberRepository.save(member);
        String title = "제목";
        String content = "내용";
        Long findBlog = blogService.create(member.getId(), title, content);
        Blog blog = blogRepository.findById(findBlog).orElseThrow();

        //when
        String modifiedTitle = "수정된 제목";
        String modifiedContent = "수정된 내용";
        blogService.update(blog.getId(), modifiedTitle, modifiedContent);

        //then
        assertEquals("수정된 제목", blog.getTitle());
        assertEquals("수정된 내용", blog.getContent());
    }

    @Test
    void delete() {
        //given
        Member member = new Member();
        member.setId("bookpark");
        memberRepository.save(member);
        String title = "제목";
        String content = "내용";
        Long findBlog = blogService.create(member.getId(), title, content);
        Blog blog = blogRepository.findById(findBlog).orElseThrow();

        //when
        blogService.delete(blog.getId());

        //then
        NoSuchElementException thrown = assertThrows(NoSuchElementException.class, () -> blogService.delete(blog.getId()));
        assertEquals("No value present", thrown.getMessage());
    }
}
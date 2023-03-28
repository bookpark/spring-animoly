package toy.animoly.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import toy.animoly.entity.Blog;
import toy.animoly.entity.User;
import toy.animoly.repository.BlogRepository;
import toy.animoly.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class BlogServiceTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private BlogService blogService;

    @Test
    void create() {
        //given
        User user = new User();
        user.setId("bookpark");
        userRepository.save(user);
        String title = "제목";
        String content = "내용";

        //when
        Long findBlog = blogService.create(user.getId(), title, content);
        Blog blog = blogRepository.findById(findBlog).orElseThrow();

        //then
        assertEquals("제목", blog.getTitle());
        assertEquals("내용", blog.getContent());
    }

    @Test
    void update() {
        //given
        User user = new User();
        user.setId("bookpark");
        userRepository.save(user);
        String title = "제목";
        String content = "내용";
        Long findBlog = blogService.create(user.getId(), title, content);
        Blog blog = blogRepository.findById(findBlog).orElseThrow();

        //when
        String modifiedTitle = "수정된 제목";
        String modifiedContent = "수정된 내용";
        blogService.update(blog.getId(), modifiedTitle, modifiedContent);

        //then
        assertEquals("수정된 제목", blog.getTitle());
        assertEquals("수정된 내용", blog.getContent());
    }
}
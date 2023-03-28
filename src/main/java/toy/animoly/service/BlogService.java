package toy.animoly.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toy.animoly.entity.Blog;
import toy.animoly.entity.User;
import toy.animoly.repository.BlogRepository;
import toy.animoly.repository.UserRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepository blogRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long create(String id, String title, String content) {
        User user = userRepository.findById(id).orElseThrow();
        Blog blog = Blog.createBlog(user, title, content);
        blogRepository.save(blog);
        return blog.getId();
    }

    @Transactional
    public void update(Long id, String title, String content) {
        Blog blog = blogRepository.findById(id).orElseThrow();
        blog.setTitle(title);
        blog.setContent(content);
        blogRepository.save(blog);
    }

    @Transactional
    public void delete(Long id) {
        Blog blog = blogRepository.findById(id).orElseThrow();
        blogRepository.delete(blog);
    }
}

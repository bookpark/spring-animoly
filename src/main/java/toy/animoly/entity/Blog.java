package toy.animoly.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blog_id")
    private Long id;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String content;
    private LocalDateTime createdAt;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    // 연관관계 메서드 //
    public void setUser(User user) {
        this.user = user;
        user.getBlogs().add(this);
    }

    // 생성 메서드 //
    public static Blog createBlog(User user) {
        Blog blog = new Blog();
        blog.setUser(user);
        blog.setTitle(blog.getTitle());
        blog.setContent(blog.getContent());
        blog.setCreatedAt(LocalDateTime.now());
        return blog;
    }

}

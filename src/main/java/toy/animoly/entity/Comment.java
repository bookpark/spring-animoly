package toy.animoly.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private Boolean isModified;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    private Blog blog;

    // 연관관계 메서드 //
    public void setUser(User user) {
        this.user = user;
        user.getComments().add(this);
    }

    public void setPost(Post post) {
        this.post = post;
        post.getComments().add(this);
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
        blog.getComments().add(this);
    }

    // 생성 메서드 //
    public static Comment createPostComment(User user, Post post) {
        Comment comment = new Comment();
        comment.setUser(user);
        comment.setPost(post);
        comment.setContent(post.getContent());
        comment.setCreatedAt(LocalDateTime.now());
        return comment;
    }

    public static Comment createBlogComment(User user, Blog blog) {
        Comment comment = new Comment();
        comment.setUser(user);
        comment.setBlog(blog);
        comment.setContent(blog.getContent());
        comment.setCreatedAt(LocalDateTime.now());
        return comment;
    }

}

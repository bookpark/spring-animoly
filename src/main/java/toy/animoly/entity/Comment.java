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
    private Member member;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    private Blog blog;

    // 연관관계 메서드 //
    public void setMember(Member member) {
        this.member = member;
        member.getComments().add(this);
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
    public static Comment createPostComment(Member member, Post post) {
        Comment comment = new Comment();
        comment.setMember(member);
        comment.setPost(post);
        comment.setContent(post.getContent());
        comment.setCreatedAt(LocalDateTime.now());
        return comment;
    }

    public static Comment createBlogComment(Member member, Blog blog) {
        Comment comment = new Comment();
        comment.setMember(member);
        comment.setBlog(blog);
        comment.setContent(blog.getContent());
        comment.setCreatedAt(LocalDateTime.now());
        return comment;
    }

}

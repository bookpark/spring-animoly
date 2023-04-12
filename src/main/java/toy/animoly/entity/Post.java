package toy.animoly.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;
    private String title;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;
    private LocalDateTime createdAt;
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Member member;

    // 연관관계 메서드 //
    public void setMember(Member member) {
        this.member = member;
        member.getPosts().add(this);
    }

    // 생성 메서드 //
    public static Post createPost(Member member) {
        Post post = new Post();
        post.setMember(member);
        post.setTitle(post.getTitle());
        post.setContent(post.getContent());
        post.setCreatedAt(LocalDateTime.now());
        return post;
    }

}

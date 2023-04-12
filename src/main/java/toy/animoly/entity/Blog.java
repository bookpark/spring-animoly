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
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blog_id")
    private Long id;
    private String title;
    @Column(columnDefinition = "LONGTEXT")
    private String content;
    private LocalDateTime createdAt;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Member member;
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    // 연관관계 메서드 //
    public void setMember(Member member) {
        this.member = member;
        member.getBlogs().add(this);
    }

    // 생성 메서드 //
    public static Blog createBlog(Member member, String title, String content) {
        Blog blog = new Blog();
        blog.setMember(member);
        blog.setTitle(title);
        blog.setContent(content);
        blog.setCreatedAt(LocalDateTime.now());
        return blog;
    }

}

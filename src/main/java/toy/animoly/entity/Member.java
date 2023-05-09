package toy.animoly.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member implements Persistable<String> {

    @Id
    @Column(name = "user_id")
    private String id;
    private String password;
    private String name;
    private String nickname;
    private String phoneNumber;

    @Embedded
    private Address address;

    @Column
    private String role;

    @CreatedDate
    private LocalDateTime createdDate;

    @OneToMany(mappedBy = "member")
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Blog> blogs = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Adoption> adoptions = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Bookmark> bookmarks = new ArrayList<>();

    @Override
    public boolean isNew() {
        return createdDate == null;
    }

}

package toy.animoly.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {
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
}

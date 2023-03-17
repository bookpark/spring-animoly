package toy.animoly.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Bookmark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookmark_id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", unique = true)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "animal_id", unique = true)
    private Animal animal;

    // 연관관계 메서드 //
    public void setUser(User user) {
        this.user = user;
        user.getBookmarks().add(this);
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
        animal.getBookmarks().add(this);
    }

    // 생성 메서드 //
    public static Bookmark createBookmark(User user, Animal animal) {
        Bookmark bookmark = new Bookmark();
        bookmark.setUser(user);
        bookmark.setAnimal(animal);
        return bookmark;
    }

}

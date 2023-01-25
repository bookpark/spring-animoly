package toy.animoly.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "animal_id")
    private Long id;
    @OneToMany(mappedBy = "animal")
    private List<Adoption> adoptions = new ArrayList<>();
    @OneToMany(mappedBy = "animal")
    private List<Bookmark> bookmarks = new ArrayList<>();

}

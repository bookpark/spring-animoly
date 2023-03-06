package toy.animoly.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Adoption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "adoption_id")
    private Long id;
    private LocalDateTime applyDate;
    @Enumerated(EnumType.STRING)
    private AdoptionStatus status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "animal_id")
    private Animal animal;

    // 연관관계 메서드 //
    public void setUser(User user) {
        this.user = user;
        user.getAdoptions().add(this);
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
        animal.setAdoption(this);
    }

}

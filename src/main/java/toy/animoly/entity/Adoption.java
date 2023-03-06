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

    // 생성 메서드 //
    public static Adoption createAdoption(User user, Animal animal) {
        Adoption adoption = new Adoption();
        adoption.setUser(user);
        adoption.setAnimal(animal);
        adoption.setStatus(AdoptionStatus.APPLIED);
        adoption.setApplyDate(LocalDateTime.now());
        return adoption;
    }

    // 로직

    /**
     * 입양 취소
     */
    public void cancel() {
        if (this.getStatus() == AdoptionStatus.COMPLETED) {
            throw new IllegalStateException("이미 처리 된 입양 건은 취소가 불가능합니다.");
        }
        this.setStatus(AdoptionStatus.CANCELED);
    }

}

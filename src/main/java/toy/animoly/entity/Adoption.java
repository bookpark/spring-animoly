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
    private Member member;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "animal_id")
    private Animal animal;

    // 연관관계 메서드 //
    public void setMember(Member member) {
        this.member = member;
        member.getAdoptions().add(this);
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
        animal.getAdoptions().add(this);
    }

    // 생성 메서드 //
    public static Adoption createAdoption(Member member, Animal animal) {
        Adoption adoption = new Adoption();
        adoption.setMember(member);
        adoption.setAnimal(animal);
        adoption.setStatus(AdoptionStatus.APPLIED);
        adoption.setApplyDate(LocalDateTime.now());
        return adoption;
    }

    // 비즈니스 로직
    /**
     * 입양 절차 시작
     */
    public void processAdoption() {
        if (this.getStatus() == AdoptionStatus.CANCEL_REQUESTED) {
            throw new IllegalStateException("입양 취소 요청 건입니다.");
        }
        this.setStatus(AdoptionStatus.PROCESSING);
    }

    /**
     * 입양 절차 완료
     */
    public void completeAdoption() {
        this.setStatus(AdoptionStatus.COMPLETED);
    }

    /**
     * 입양 취소 요청
     */
    public void requestCancel() {
        if (this.getStatus() == AdoptionStatus.COMPLETED) {
            throw new IllegalStateException("이미 처리 된 입양 건은 취소가 불가능합니다.");
        }
        this.setStatus(AdoptionStatus.CANCEL_REQUESTED);
    }

    /**
     * 입양 취소 승인
     */
    public void approveCancel() {
        this.setStatus(AdoptionStatus.CANCELLED);
    }

}

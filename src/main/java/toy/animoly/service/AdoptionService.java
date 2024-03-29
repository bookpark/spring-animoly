package toy.animoly.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toy.animoly.entity.Adoption;
import toy.animoly.entity.Animal;
import toy.animoly.entity.Member;
import toy.animoly.repository.AdoptionRepository;
import toy.animoly.repository.AnimalRepository;
import toy.animoly.repository.MemberRepository;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AdoptionService {
    private final AdoptionRepository adoptionRepository;
    private final MemberRepository memberRepository;
    private final AnimalRepository animalRepository;

    /**
     * 단건 조회
     */
    public Optional<Adoption> findById(Long id) {
        return adoptionRepository.findById(id);
    }

    /**
     * 입양 신청
     */
    @Transactional
    public Long apply(String userId, Long animalId) {
        // 엔티티 조회
        Member member = memberRepository.findById(userId).orElseThrow();
        Animal animal = animalRepository.findById(animalId).orElseThrow();

        // 신청서 생성
        Adoption adoption = Adoption.createAdoption(member, animal);

        // 저장
        adoptionRepository.save(adoption);

        return adoption.getId();
    }

    /**
     * 입양 취소 신청
     */
    @Transactional
    public void requestCancel(Long adoptionId) {
        Adoption adoption = adoptionRepository.findById(adoptionId).orElseThrow();
        adoption.requestCancel();
    }

    /**
     * 입양 취소 승인
     */
    @Transactional
    public void approveCancel(Long adoptionId) {
        Adoption adoption = adoptionRepository.findById(adoptionId).orElseThrow();
        adoption.approveCancel();
    }
}

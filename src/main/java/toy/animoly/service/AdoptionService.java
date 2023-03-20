package toy.animoly.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toy.animoly.entity.Adoption;
import toy.animoly.entity.Animal;
import toy.animoly.entity.User;
import toy.animoly.repository.AdoptionRepository;
import toy.animoly.repository.AnimalRepository;
import toy.animoly.repository.UserRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AdoptionService {
    private final AdoptionRepository adoptionRepository;
    private final UserRepository userRepository;
    private final AnimalRepository animalRepository;

    /**
     * 입양 신청
     */
    @Transactional
    public Long apply(String userId, Long animalId) {
        // 엔티티 조회
        User user = userRepository.findById(userId).orElseThrow();
        Animal animal = animalRepository.findById(animalId).orElseThrow();

        // 신청서 생성
        Adoption adoption = Adoption.createAdoption(user, animal);

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

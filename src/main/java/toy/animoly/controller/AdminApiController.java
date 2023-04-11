package toy.animoly.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import toy.animoly.entity.Adoption;
import toy.animoly.entity.AdoptionStatus;
import toy.animoly.service.AdoptionService;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class AdminApiController {
    private final AdoptionService adoptionService;

    /**
     * 입양 절차 시작 API
     */
    @PostMapping("/api/admin/adoptions/{id}/process-adoption")
    public AdoptionStatus processAdoption(@PathVariable Long id) {
        Adoption adoption = adoptionService.findById(id).orElseThrow();
        adoption.processAdoption();
        return adoption.getStatus();
    }

    /**
     * 입양 절차 완료 API
     */
    @PostMapping("/api/admin/adoptions{id}/complete-adoption")
    public AdoptionStatus completeAdoption(@PathVariable Long id) {
        Adoption adoption = adoptionService.findById(id).orElseThrow();
        adoption.completeAdoption();
        return adoption.getStatus();
    }

    /**
     * 입양 취소 승인 API
     */
    @PostMapping("/api/admin/adoptions/{id}/approve-cancel")
    public AdoptionStatus approveCancel(@PathVariable Long id) {
        Optional<Adoption> adoption = adoptionService.findById(id);
        adoptionService.approveCancel(id);
        return adoption.orElseThrow().getStatus();
    }
}

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
     * 입양 취소 승인 API
     */
    @PostMapping("/api/admin/adoptions/{id}/approve-cancel")
    public AdoptionStatus approveCancel(@PathVariable Long id) {
        Optional<Adoption> adoption = adoptionService.findById(id);
        adoptionService.approveCancel(id);
        return adoption.orElseThrow().getStatus();
    }
}

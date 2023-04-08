package toy.animoly.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import toy.animoly.entity.Adoption;
import toy.animoly.entity.AdoptionStatus;
import toy.animoly.service.AdoptionService;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class AdoptionApiController {
    private final AdoptionService adoptionService;

    @PostMapping("/api/adoptions/apply")
    public AdoptionStatus apply(CreateAdoptionRequest request) {
        Long id = adoptionService.apply(request.userId, request.animalId);
        return adoptionService.findById(id).orElseThrow().getStatus();
    }

    @PostMapping("/api/adoptions/{id}/request-cancel")
    public AdoptionStatus requestCancel(@PathVariable Long id) {
        Optional<Adoption> adoption = adoptionService.findById(id);
        adoptionService.requestCancel(id);
        return adoption.orElseThrow().getStatus();
    }

    @Data
    static class CreateAdoptionRequest {
        private String userId;
        private Long animalId;
    }
}

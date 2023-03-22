package toy.animoly.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import toy.animoly.service.AdoptionService;

@RestController
@RequiredArgsConstructor
public class AdoptionApiController {
    private final AdoptionService adoptionService;

    @PostMapping("/api/apply")
    public Long apply(@RequestBody CreateAdoptionRequest request) {
        return adoptionService.apply(request.userId, request.animalId);
    }

    @Data
    static class CreateAdoptionRequest {
        private String userId;
        private Long animalId;
    }
}

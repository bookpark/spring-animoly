package toy.animoly.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import toy.animoly.dto.CancelAdoptionResponse;
import toy.animoly.dto.CreateAdoptionRequest;
import toy.animoly.dto.CreateAdoptionResponse;
import toy.animoly.entity.Adoption;
import toy.animoly.entity.AdoptionStatus;
import toy.animoly.service.AdoptionService;

@RestController
@RequiredArgsConstructor
public class AdoptionApiController {

    private final AdoptionService adoptionService;

    @PostMapping("/api/adoptions/apply")
    public CreateAdoptionResponse apply(CreateAdoptionRequest request) {
        Long id = adoptionService.apply(request.getUserId(), request.getAnimalId());
        Adoption adoption = adoptionService.findById(id).orElseThrow();
        String userId = adoption.getMember().getId();
        Long animalId = adoption.getAnimal().getDesertionNo();
        AdoptionStatus status = adoption.getStatus();
        return new CreateAdoptionResponse(id, userId, animalId, status);
    }

    @PostMapping("/api/adoptions/{id}/request-cancel")
    public CancelAdoptionResponse requestCancel(@PathVariable Long id) {
        adoptionService.requestCancel(id);
        Adoption adoption = adoptionService.findById(id).orElseThrow();
        Long adoptionId = adoption.getId();
        AdoptionStatus status = adoption.getStatus();
        return new CancelAdoptionResponse(adoptionId, status);
    }

}

package toy.animoly.dto;

import lombok.Data;
import toy.animoly.entity.AdoptionStatus;

@Data
public class CreateAdoptionResponse {
    private Long id;
    private String userId;
    private Long animalId;
    private AdoptionStatus status;

    public CreateAdoptionResponse(Long id, String userId, Long animalId, AdoptionStatus status) {
        this.id = id;
        this.userId = userId;
        this.animalId = animalId;
        this.status = status;
    }
}

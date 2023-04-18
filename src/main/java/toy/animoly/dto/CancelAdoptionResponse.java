package toy.animoly.dto;

import lombok.Data;
import toy.animoly.entity.AdoptionStatus;

@Data
public class CancelAdoptionResponse {
    private Long id;
    private AdoptionStatus status;

    public CancelAdoptionResponse(Long adoptionId, AdoptionStatus status) {
        this.id = adoptionId;
        this.status = status;
    }
}

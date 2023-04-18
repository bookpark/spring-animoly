package toy.animoly.dto;

import lombok.Data;

@Data
public class CreateAdoptionRequest {
    private String userId;
    private Long animalId;
}
package toy.animoly.dto;

import lombok.Data;

@Data
public class CreateUserResponse {
    private String id;

    public CreateUserResponse(String id) {
        this.id = id;
    }
}

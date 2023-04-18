package toy.animoly.dto;

import lombok.Data;

@Data
public class UpdateUserResponse {
    private String id;
    private String nickname;

    public UpdateUserResponse(String id, String nickname) {
        this.id = id;
        this.nickname = nickname;
    }
}
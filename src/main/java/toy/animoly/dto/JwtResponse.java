package toy.animoly.dto;

import lombok.Data;

@Data
public class JwtResponse {
    private String id;
    private final String token;

    public JwtResponse(String id, String token) {
        this.id = id;
        this.token = token;
    }
}

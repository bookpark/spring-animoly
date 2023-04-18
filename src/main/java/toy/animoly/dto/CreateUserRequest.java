package toy.animoly.dto;

import lombok.Data;

@Data
public class CreateUserRequest {
    private String id;
    private String password;
    private String name;
    private String nickname;
    private String phoneNumber;
    private String address1;
    private String address2;
    private String zipcode;
}

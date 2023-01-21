package toy.animoly.entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class User {

    @Id
    @Column(name = "user_id")
    private String id;
    private String password;
    private String nickname;
    private String phoneNumber;
    @Embedded
    private Address address;
    @Column
    private String role;

}

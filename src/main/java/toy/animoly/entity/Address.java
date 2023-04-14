package toy.animoly.entity;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Address {

    private String address1;
    private String address2;
    private String zipcode;
    protected Address() {
    }
    public Address(String address1, String address2, String zipcode) {
        this.address1 = address1;
        this.address2 = address2;
        this.zipcode = zipcode;
    }

}

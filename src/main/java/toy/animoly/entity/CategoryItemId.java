package toy.animoly.entity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
public class CategoryItemId implements Serializable {

    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "item_id")
    private Long itemId;

}

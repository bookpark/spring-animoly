package toy.animoly.entity;

import lombok.Getter;
import toy.animoly.entity.item.Item;

import javax.persistence.*;

@Entity
@Getter
public class CategoryItem {

    @EmbeddedId
    private CategoryItemId id = new CategoryItemId();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", insertable = false, updatable = false)
    private Item item;

}

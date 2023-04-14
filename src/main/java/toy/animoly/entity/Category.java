package toy.animoly.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    private String name;

    private int depth;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_category_id")
    private Category parentCategory;

    @OneToMany(mappedBy = "parentCategory", cascade = CascadeType.ALL)
    private List<Category> childCategories = new ArrayList<>();

    @OneToMany(mappedBy = "category")
    private List<CategoryItem> categoryItems = new ArrayList<>();

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }

    // 연관관계 메서드 //
    public void addChildCategory(Category child) {
        this.childCategories.add(child);
        child.setParentCategory(this);
    }

    // 생성 메서드 //
    /**
     * 카테고리 추가
     */
    public static Category createCategory(Category child) {
        Category category = new Category();
        category.addChildCategory(child);
        return category;
    }

}

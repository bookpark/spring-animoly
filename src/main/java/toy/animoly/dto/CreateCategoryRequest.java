package toy.animoly.dto;

import lombok.Data;

@Data
public class CreateCategoryRequest {
    private String parentName;
    private String newName;
}

package toy.animoly.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AnimalListDto {
    private Long desertionNo;
    private String filename;
    private String ageCd;
    private String sexCd;
    private String processState;
    private String happenDt;
}

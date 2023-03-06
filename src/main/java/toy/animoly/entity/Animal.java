package toy.animoly.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.awt.print.Book;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "animal_id")
    private Long id;
    private Long desertionNo;
    private String filename;
    private LocalDateTime happenDt;
    private String happenPlace;
    private String kindCd;
    private String colorCd;
    private Integer age;
    private Integer weight;
    private Long noticeNo;
    private LocalDateTime noticeSdt;
    private LocalDateTime noticeEdt;
    private String popfile;
    private String processState;
    private String sexCd;
    private Boolean neuterYn;
    private String specialMark;
    private String careNm;
    private String careTel;
    private String careAddr;
    private String orgNm;
    private String chargeNm;
    private String officetel;
    private String noticeComment;
    @JsonIgnore
    @OneToOne(mappedBy = "animal", fetch = FetchType.LAZY)
    private Adoption adoption;
    @OneToMany(mappedBy = "animal")
    private List<Bookmark> bookmarks = new ArrayList<>();

}

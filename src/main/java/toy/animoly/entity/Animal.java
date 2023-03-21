package toy.animoly.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
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
    private String happenDt;
    private String happenPlace;
    private String kindCd;
    private String colorCd;
    private String age;
    private String weight;
    private String noticeNo;
    private Date noticeSdt;
    private Date noticeEdt;
    private String popfile;
    private String processState;
    private String sexCd;
    private String neuterYn;
    private String specialMark;
    private String careNm;
    private String careTel;
    private String careAddr;
    private String orgNm;
    private String chargeNm;
    private String officetel;
    private String noticeComment;
    @JsonIgnore
    @OneToMany(mappedBy = "animal")
    private List<Adoption> adoptions = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "animal")
    private List<Bookmark> bookmarks = new ArrayList<>();
}

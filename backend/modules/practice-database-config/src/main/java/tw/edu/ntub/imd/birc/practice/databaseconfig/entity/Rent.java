package tw.edu.ntub.imd.birc.practice.databaseconfig.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import lombok.*;

@Getter @Setter @ToString @Builder @NoArgsConstructor @AllArgsConstructor
@Entity @Table(name = "rent")
public class Rent {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rent_id")
    private Integer rentId;

    @Column(name = "year")
    private Integer year;

    @Column(name = "category_id")
    private Integer categoryId;

    @Column(name = "item")
    private String item;

    @Column(name = "remark")
    private String remark;

    @Column(name = "photo_link")
    private String photoLink;

    @Builder.Default
    @Column(name = "rent_enable")
    private Boolean rentEnable = true;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "modify_time")
    private LocalDateTime modifyTime;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private Category category;
    // 已移除 user / photo 關聯
}
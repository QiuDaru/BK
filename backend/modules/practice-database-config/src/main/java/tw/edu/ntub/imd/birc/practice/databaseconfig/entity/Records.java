package tw.edu.ntub.imd.birc.practice.databaseconfig.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "records")
public class Records {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "records_id")
    private Integer recordsId;

    @Column(name = "rent_id")
    private Integer rentId;

    /**
     * 商家 ID
     */
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "category_id")
    private Integer categoryId;

    @Builder.Default
    @Column(name = "enable")
    private Boolean enable = true;

    @Column(name = "return_date")
    private LocalDateTime returnDate;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "modify_time")
    private LocalDateTime modifyTime;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rent_id", insertable = false, updatable = false)
    private Rent rent;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;
}
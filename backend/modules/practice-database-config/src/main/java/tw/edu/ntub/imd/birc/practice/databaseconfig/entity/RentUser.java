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
@Table(name = "rent_user")
public class RentUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rent_user_id")
    private Integer rentUserId;

    /**
     * 借入人 ID
     */
    @Column(name = "borrow_user_id")
    private Integer borrowUserId;

    /**
     * 借出人 ID
     */
    @Column(name = "lend_user_id_2")
    private Integer lendUserId2;

    @Column(name = "role_id")
    private Integer roleId;

    @Column(name = "file_no")
    private Integer fileNo;

    @Column(name = "chinese_name")
    private String chineseName;

    @Column(name = "phone_number")
    private Integer phoneNumber;

    @Column(name = "phone_number_2")
    private Integer phoneNumber2;

    @Column(name = "home_place")
    private String homePlace;

    @Builder.Default
    @Column(name = "enable")
    private Boolean enable = true;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "modify_time")
    private LocalDateTime modifyTime;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "borrow_user_id", insertable = false, updatable = false)
    private User borrowUser;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lend_user_id_2", insertable = false, updatable = false)
    private User lendUser;
}
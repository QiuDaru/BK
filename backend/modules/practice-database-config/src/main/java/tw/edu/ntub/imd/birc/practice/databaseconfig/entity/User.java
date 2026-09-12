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
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "role_id")
    private Integer roleId;

    @Column(name = "file_no")
    private Integer fileNo;

    @Column(name = "chinese_name")
    private String chineseName;

    @Column(name = "gender")
    private String gender;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private Integer phoneNumber;

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
    @JoinColumn(name = "role_id", insertable = false, updatable = false)
    private Role role;
}
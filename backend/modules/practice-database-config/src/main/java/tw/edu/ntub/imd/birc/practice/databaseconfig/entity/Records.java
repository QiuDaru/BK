package tw.edu.ntub.imd.birc.practice.databaseconfig.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Entity @Table(name = "records")
public class Records {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "records_id")
    private Integer recordsId;

    @Column(name = "rent_id")         private Integer rentId;
    @Column(name = "lend_user_id")    private Integer lendUserId;   // 出借人
    @Column(name = "borrow_user_id")  private Integer borrowUserId; // 借用人

    @Column(name = "enable")
    private Boolean enable;           // 1=借用中, 0=已歸還

    @Column(name = "return_date")
    private LocalDateTime returnDate; // 預計歸還

    @Column(name = "create_time")
    private LocalDateTime createTime; // 借出時間

    @Column(name = "borrow_photo_link")
    private String borrowPhotoLink;

    @Column(name = "lend_photo_link")
    private String returnPhotoLink;
}
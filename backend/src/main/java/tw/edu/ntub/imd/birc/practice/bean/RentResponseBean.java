package tw.edu.ntub.imd.birc.practice.bean;

import lombok.*;
import java.time.LocalDateTime;

@Getter @Builder
public class RentResponseBean {
    private Integer rentId;
    private Integer year;
    private String item;
    private String categoryName;   // 來自 category 關聯
    private String photoLink;      // 來自 photo 關聯
    private Integer ownerId;
    private String ownerName;      // 來自 user 關聯
    private String remark;
    private Boolean rentEnable;
    private LocalDateTime createTime;
}
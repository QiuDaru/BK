package tw.edu.ntub.imd.birc.practice.bean;

import lombok.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Getter @Setter
public class RentBean {
    private Integer id;                 // = rentId

    @NotNull(message = "年度不得為空")
    private Integer year;

    @NotNull(message = "分享者不得為空")
    private Integer userId;

    @NotNull(message = "分類不得為空")
    private Integer categoryId;

    @NotBlank(message = "物品名稱不得為空")
    private String item;

    @NotNull(message = "照片不得為空")
    private Integer photoId;

    private String remark;

    // ↓ 回應用（唯讀，前端不需帶）
    private Boolean rentEnable;
    private LocalDateTime createTime;
    private String categoryName;
    private String photoLink;
    private String ownerName;
}
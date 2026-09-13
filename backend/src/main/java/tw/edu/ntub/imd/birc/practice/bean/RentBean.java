package tw.edu.ntub.imd.birc.practice.bean;

import lombok.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Data
public class RentBean {
    private Integer id;
    @NotNull(message = "年度不得為空") private Integer year;
    @NotNull(message = "分類不得為空") private Integer categoryId;
    @NotBlank(message = "物品名稱不得為空") private String item;
    private String remark;
    private String photoLink;
    private Integer ownerId;     // 物主 id（回應）
    private String ownerName;    // 物主姓名（回應）
    // 唯讀
    private Boolean rentEnable;
    private LocalDateTime createTime;
    private String categoryName;
}
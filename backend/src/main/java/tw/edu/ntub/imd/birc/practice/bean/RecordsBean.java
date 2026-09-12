package tw.edu.ntub.imd.birc.practice.bean;

import lombok.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter @Setter
public class RecordsBean {
    private Integer id;

    @NotNull(message = "物品不得為空")
    private Integer rentId;

    @NotNull(message = "借用人不得為空")
    private Integer userId;

    @NotNull(message = "預計歸還日不得為空")
    private LocalDateTime dueDate;   // 對應 records.return_date

    // ↓ 回應用（唯讀）
    private Integer categoryId;
    private String status;           // BORROWED / RETURNED
    private boolean overdue;
    private LocalDateTime returnDate;
    private LocalDateTime createTime; // 借出時間
    private LocalDateTime modifyTime; // 實際歸還時間
}
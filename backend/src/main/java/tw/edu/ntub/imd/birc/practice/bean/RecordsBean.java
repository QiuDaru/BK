package tw.edu.ntub.imd.birc.practice.bean;

import lombok.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class RecordsBean {
    private Integer id;
    @NotNull(message = "物品不得為空")   private Integer rentId;
    @NotNull(message = "出借人不得為空") private Integer lendUserId;
    @NotNull(message = "借用人不得為空") private Integer borrowUserId;
    @NotNull(message = "預計歸還日不得為空") private LocalDateTime dueDate;
    // 唯讀
    private String status;
    private boolean overdue;
    private LocalDateTime returnDate;
    private LocalDateTime createTime;
}
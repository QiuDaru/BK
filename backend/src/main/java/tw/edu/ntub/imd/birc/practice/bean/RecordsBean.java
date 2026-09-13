package tw.edu.ntub.imd.birc.practice.bean;

import lombok.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class RecordsBean {
    private Integer id;
    @NotNull(message = "物品不得為空")   private Integer rentId;
    private Integer lendUserId;
    @NotNull(message = "預計歸還日不得為空") private LocalDateTime dueDate;
    private Integer borrowUserId;
    private String borrowPhotoLink;
    private String returnPhotoLink;
    // 唯讀
    private String status;
    private boolean overdue;
    private LocalDateTime returnDate;
    private LocalDateTime createTime;
}
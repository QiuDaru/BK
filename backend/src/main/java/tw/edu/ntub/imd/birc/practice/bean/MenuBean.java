package tw.edu.ntub.imd.birc.practice.bean;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MenuBean {
    private Integer id;
    private Integer itemID;
    private Integer amount;
    private Integer total;
    private LocalDateTime create_time;
    private ItemBean item;
}

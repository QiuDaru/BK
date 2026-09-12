package tw.edu.ntub.imd.birc.practice.bean;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ItemBean {
    private Integer id;
    private String name;
    private Integer price;
//    private LocalDateTime create_time;
}

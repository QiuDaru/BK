package tw.edu.ntub.imd.birc.practice.bean;

import lombok.*;

@Getter @Setter
public class RentCreateRequestBean {
    private Integer year;
    private Integer userId;      // 物主
    private Integer categoryId;
    private String item;
    private Integer photoId;
    private String remark;
}
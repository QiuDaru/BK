package tw.edu.ntub.imd.birc.practice.databaseconfig.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Immutable;
import javax.persistence.*;

@Getter @Setter
@Entity
@Immutable                       // 唯讀，Hibernate 不會嘗試寫入
@Table(name = "v_rent_category")
public class VRentCategory {
    @Id
    @Column(name = "category")
    private String category;     // 類型（當作主鍵，view 裡每個分類唯一）

    @Column(name = "total")
    private Long total;          // 類型統計
}
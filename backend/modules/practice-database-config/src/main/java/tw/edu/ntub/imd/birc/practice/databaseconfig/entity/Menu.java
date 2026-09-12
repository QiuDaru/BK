package tw.edu.ntub.imd.birc.practice.databaseconfig.entity;

import lombok.Data;
import tw.edu.ntub.imd.birc.practice.databaseconfig.Config;

import javax.persistence.*;
import tw.edu.ntub.imd.birc.practice.databaseconfig.entity.Listener.MenuListener;
import java.time.LocalDateTime;

@Data
@Entity
@EntityListeners(MenuListener.class)
@Table(name = "menu", schema = Config.DATABASE_NAME)
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "itemId", nullable = false)
    private Item item;

    @Column(name = "amount", nullable = false)
    private Integer amount;

    @Column(name = "total", nullable = false)
    private Integer total;

    @PrePersist
    @PreUpdate
    public void calculateTotal() {
        if (this.item != null && this.amount != null) {
            this.total = this.amount * this.item.getPrice();
        }
    }

    @Column(name = "create_time", nullable = false)
    private LocalDateTime create_time;

}

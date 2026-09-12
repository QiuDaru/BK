package tw.edu.ntub.imd.birc.practice.databaseconfig.entity;


import lombok.Data;
import tw.edu.ntub.imd.birc.practice.databaseconfig.Config;
import tw.edu.ntub.imd.birc.practice.databaseconfig.entity.Listener.ItemListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@EntityListeners(ItemListener.class)
@Table(name = "item", schema = Config.DATABASE_NAME)
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "create_time", nullable = false)
    private LocalDateTime create_time;
}

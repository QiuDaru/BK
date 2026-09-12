package tw.edu.ntub.imd.birc.practice.databaseconfig.entity.Listener;

import tw.edu.ntub.imd.birc.practice.databaseconfig.entity.Item;

import javax.persistence.PrePersist;
import java.time.LocalDateTime;

public class ItemListener {
    @PrePersist
    public void PreSave(Item item) {
        if (item.getCreate_time() == null) {
            item.setCreate_time(LocalDateTime.now());
        }
    }
}

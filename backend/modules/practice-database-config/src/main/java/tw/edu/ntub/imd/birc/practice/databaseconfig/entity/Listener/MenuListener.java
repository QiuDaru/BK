package tw.edu.ntub.imd.birc.practice.databaseconfig.entity.Listener;

import tw.edu.ntub.imd.birc.practice.databaseconfig.entity.Menu;

import javax.persistence.PrePersist;
import java.time.LocalDateTime;

public class MenuListener {
    @PrePersist
    public void PreSave(Menu menu) {
        if (menu.getCreate_time() == null) {
            menu.setCreate_time(LocalDateTime.now());
        }
    }

}

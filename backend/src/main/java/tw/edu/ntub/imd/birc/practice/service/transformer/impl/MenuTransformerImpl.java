package tw.edu.ntub.imd.birc.practice.service.transformer.impl;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import tw.edu.ntub.birc.common.util.JavaBeanUtils;
import tw.edu.ntub.imd.birc.practice.bean.MenuBean;
import tw.edu.ntub.imd.birc.practice.databaseconfig.entity.Item;
import tw.edu.ntub.imd.birc.practice.databaseconfig.entity.Menu;
import tw.edu.ntub.imd.birc.practice.service.transformer.MenuTransformer;

@Component
public class MenuTransformerImpl implements MenuTransformer {
    @Override
    public Menu transferToEntity(@NonNull MenuBean menuBean) {
        Menu menu = JavaBeanUtils.copy(menuBean, new Menu());

        if (menuBean.getItemID() != null) {
            Item item = new Item();
            item.setId(menuBean.getItemID());
            menu.setItem(item);
        }

        return menu;
    }

    @Override
    public MenuBean transferToBean(@NonNull Menu menu) {
        MenuBean menuBean = JavaBeanUtils.copy(menu, new MenuBean());

        if (menu.getItem() != null) {
            menuBean.setItemID(menu.getItem().getId());
        }

        return menuBean;
    }
}

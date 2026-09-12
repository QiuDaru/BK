package tw.edu.ntub.imd.birc.practice.service.impl;

import org.springframework.stereotype.Service;
import tw.edu.ntub.imd.birc.practice.bean.MenuBean;
import tw.edu.ntub.imd.birc.practice.databaseconfig.dao.ItemDAO;
import tw.edu.ntub.imd.birc.practice.databaseconfig.dao.MenuDAO;
import tw.edu.ntub.imd.birc.practice.databaseconfig.entity.Menu;
import tw.edu.ntub.imd.birc.practice.service.MenuService;
import tw.edu.ntub.imd.birc.practice.service.transformer.MenuTransformer;

@Service
public class MenuServiceImpl extends BaseServiceImpl<MenuBean, Menu, Integer> implements MenuService {
    private final MenuDAO menuDAO;
    private final MenuTransformer menuTransformer;
    private final ItemDAO itemDAO;

    public MenuServiceImpl(MenuDAO dao, MenuTransformer transformer, ItemDAO itemDAO) {
        super(dao, transformer);
        this.menuDAO = dao;
        this.menuTransformer = transformer;
        this.itemDAO = itemDAO;
    }

    @Override
    public MenuBean save(MenuBean menuBean) {
        Menu menu = menuTransformer.transferToEntity(menuBean);

        if (menuBean.getItemID() != null){
            menu.setItem(
                    itemDAO.findById(menuBean.getItemID())
                            .orElseThrow(() -> new RuntimeException("品項不存在"))
            );
        }
        menu = menuDAO.save(menu);
        return menuTransformer.transferToBean(menu);
    }

    @Override
    public void update(Integer id, MenuBean menuBean) {
        Menu menu = menuDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu不存在"));

        if (menuBean.getItemID() != null) {
            menu.setItem(
                    itemDAO.findById(menuBean.getItemID())
                            .orElseThrow(() -> new RuntimeException("品項不存在"))
            );
        }

        if (menuBean.getAmount() != null) {
            menu.setAmount(menuBean.getAmount());
        }

        menuDAO.save(menu);
    }
}

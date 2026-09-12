package tw.edu.ntub.imd.birc.practice.service.impl;

import org.springframework.stereotype.Service;
import tw.edu.ntub.imd.birc.practice.bean.ItemBean;
import tw.edu.ntub.imd.birc.practice.databaseconfig.dao.ItemDAO;
import tw.edu.ntub.imd.birc.practice.databaseconfig.entity.Item;
import tw.edu.ntub.imd.birc.practice.service.ItemService;
import tw.edu.ntub.imd.birc.practice.service.transformer.ItemTransformer;

@Service
public class ItemServiceImpl extends BaseServiceImpl<ItemBean, Item, Integer> implements ItemService {
    private final ItemDAO itemDAO;
    private final ItemTransformer itemTransformer;

    public ItemServiceImpl(ItemDAO dao, ItemTransformer transformer) {
        super(dao, transformer);
        this.itemDAO = dao;
        this.itemTransformer = transformer;
    }

    @Override
    public ItemBean save(ItemBean itemBean) {
        Item item = itemDAO.save(itemTransformer.transferToEntity(itemBean));
        return itemTransformer.transferToBean(item);
    }
}
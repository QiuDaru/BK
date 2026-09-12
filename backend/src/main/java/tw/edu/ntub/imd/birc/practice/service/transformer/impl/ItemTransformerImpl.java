package tw.edu.ntub.imd.birc.practice.service.transformer.impl;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import tw.edu.ntub.birc.common.util.JavaBeanUtils;
import tw.edu.ntub.imd.birc.practice.bean.ItemBean;
import tw.edu.ntub.imd.birc.practice.databaseconfig.entity.Item;
import tw.edu.ntub.imd.birc.practice.service.transformer.ItemTransformer;

@Component
public class ItemTransformerImpl implements ItemTransformer {
    @NonNull
    @Override
    public Item transferToEntity(@NonNull ItemBean itemBean) {
        return JavaBeanUtils.copy(itemBean, new Item());
    }

    @NonNull
    @Override
    public ItemBean transferToBean(@NonNull Item item) {
        return JavaBeanUtils.copy(item, new ItemBean());
    }
}
package tw.edu.ntub.imd.birc.practice.service.transformer.impl;

import org.springframework.stereotype.Component;
import tw.edu.ntub.imd.birc.practice.bean.RentBean;
import tw.edu.ntub.imd.birc.practice.databaseconfig.entity.Rent;
import tw.edu.ntub.imd.birc.practice.service.transformer.BeanEntityTransformer;

@Component
public class RentTransformerImpl implements BeanEntityTransformer<RentBean, Rent> {

    @Override
    public Rent transferToEntity(RentBean bean) {
        Rent rent = new Rent();
        rent.setRentId(bean.getId());
        rent.setYear(bean.getYear());
        rent.setUserId(bean.getUserId());
        rent.setCategoryId(bean.getCategoryId());
        rent.setItem(bean.getItem());
        rent.setPhotoId(bean.getPhotoId());
        rent.setRemark(bean.getRemark());
        rent.setRentEnable(bean.getRentEnable());
        return rent;   // user/category/photo 是唯讀關聯，不在這裡塞
    }

    @Override
    public RentBean transferToBean(Rent rent) {
        RentBean bean = new RentBean();
        bean.setId(rent.getRentId());
        bean.setYear(rent.getYear());
        bean.setUserId(rent.getUserId());
        bean.setCategoryId(rent.getCategoryId());
        bean.setItem(rent.getItem());
        bean.setPhotoId(rent.getPhotoId());
        bean.setRemark(rent.getRemark());
        bean.setRentEnable(rent.getRentEnable());
        bean.setCreateTime(rent.getCreateTime());
        if (rent.getCategory() != null) bean.setCategoryName(rent.getCategory().getCategory());
        if (rent.getPhoto() != null)    bean.setPhotoLink(rent.getPhoto().getLink());
        if (rent.getUser() != null)     bean.setOwnerName(rent.getUser().getChineseName());
        return bean;
    }
}
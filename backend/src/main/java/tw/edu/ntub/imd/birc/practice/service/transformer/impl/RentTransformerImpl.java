package tw.edu.ntub.imd.birc.practice.service.transformer.impl;

import org.springframework.stereotype.Component;
import tw.edu.ntub.imd.birc.practice.bean.RentBean;
import tw.edu.ntub.imd.birc.practice.databaseconfig.entity.Rent;
import tw.edu.ntub.imd.birc.practice.service.transformer.BeanEntityTransformer;

@Component
public class RentTransformerImpl implements BeanEntityTransformer<RentBean, Rent> {
    public Rent transferToEntity(RentBean b) {
        Rent r = new Rent();
        r.setRentId(b.getId());
        r.setYear(b.getYear());
        r.setCategoryId(b.getCategoryId());
        r.setItem(b.getItem());
        r.setRemark(b.getRemark());
        r.setPhotoLink(b.getPhotoLink());
        r.setRentEnable(b.getRentEnable());
        return r;
    }

    public RentBean transferToBean(Rent r) {
        RentBean b = new RentBean();
        b.setId(r.getRentId());
        b.setYear(r.getYear());
        b.setCategoryId(r.getCategoryId());
        b.setItem(r.getItem());
        b.setRemark(r.getRemark());
        b.setPhotoLink(r.getPhotoLink());
        b.setRentEnable(r.getRentEnable());
        b.setCreateTime(r.getCreateTime());
        if (r.getCategory() != null) b.setCategoryName(r.getCategory().getCategory());
        b.setOwnerId(r.getUserId());                                   // ← 新增
        if (r.getUser() != null) b.setOwnerName(r.getUser().getChineseName()); // ← 新增
        return b;
    }
}
package tw.edu.ntub.imd.birc.practice.service.transformer.impl;

import org.springframework.stereotype.Component;
import tw.edu.ntub.imd.birc.practice.bean.RecordsBean;
import tw.edu.ntub.imd.birc.practice.databaseconfig.entity.Records;
import tw.edu.ntub.imd.birc.practice.service.transformer.BeanEntityTransformer;

import java.time.LocalDateTime;

@Component
public class RecordsTransformerImpl implements BeanEntityTransformer<RecordsBean, Records> {

    @Override
    public Records transferToEntity(RecordsBean bean) {
        Records r = new Records();
        r.setRecordsId(bean.getId());
        r.setRentId(bean.getRentId());
        r.setUserId(bean.getUserId());
        r.setCategoryId(bean.getCategoryId());
        r.setReturnDate(bean.getDueDate());
        return r;
    }

    @Override
    public RecordsBean transferToBean(Records r) {
        RecordsBean bean = new RecordsBean();
        bean.setId(r.getRecordsId());
        bean.setRentId(r.getRentId());
        bean.setUserId(r.getUserId());
        bean.setCategoryId(r.getCategoryId());
        boolean borrowing = Boolean.TRUE.equals(r.getEnable());
        bean.setStatus(borrowing ? "BORROWED" : "RETURNED");
        bean.setOverdue(borrowing && r.getReturnDate() != null
                && LocalDateTime.now().isAfter(r.getReturnDate()));
        bean.setReturnDate(r.getReturnDate());
        bean.setCreateTime(r.getCreateTime());
        bean.setModifyTime(r.getModifyTime());
        return bean;
    }
}
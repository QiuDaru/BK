package tw.edu.ntub.imd.birc.practice.service.transformer.impl;

import org.springframework.stereotype.Component;
import tw.edu.ntub.imd.birc.practice.bean.RecordsBean;
import tw.edu.ntub.imd.birc.practice.databaseconfig.entity.Records;
import tw.edu.ntub.imd.birc.practice.service.transformer.BeanEntityTransformer;

import java.time.LocalDateTime;

@Component
public class RecordsTransformerImpl implements BeanEntityTransformer<RecordsBean, Records> {

    public Records transferToEntity(RecordsBean b) {
        Records r = new Records();
        r.setRecordsId(b.getId());
        r.setRentId(b.getRentId());
        r.setLendUserId(b.getLendUserId());
        r.setBorrowUserId(b.getBorrowUserId());
        r.setReturnDate(b.getDueDate());
        return r;
    }

    public RecordsBean transferToBean(Records r) {
        RecordsBean b = new RecordsBean();
        b.setId(r.getRecordsId());
        b.setRentId(r.getRentId());
        b.setLendUserId(r.getLendUserId());
        b.setBorrowUserId(r.getBorrowUserId());
        boolean borrowing = Boolean.TRUE.equals(r.getEnable());
        b.setStatus(borrowing ? "BORROWED" : "RETURNED");
        b.setOverdue(borrowing && r.getReturnDate() != null
                && LocalDateTime.now().isAfter(r.getReturnDate()));
        b.setReturnDate(r.getReturnDate());
        b.setCreateTime(r.getCreateTime());
        return b;
    }
}
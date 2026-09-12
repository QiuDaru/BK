package tw.edu.ntub.imd.birc.practice.service.impl;

import org.springframework.stereotype.Service;
import tw.edu.ntub.imd.birc.practice.bean.RecordsBean;
import tw.edu.ntub.imd.birc.practice.databaseconfig.dao.RecordsDAO;
import tw.edu.ntub.imd.birc.practice.databaseconfig.dao.RentDAO;
import tw.edu.ntub.imd.birc.practice.databaseconfig.entity.Records;
import tw.edu.ntub.imd.birc.practice.databaseconfig.entity.Rent;
import tw.edu.ntub.imd.birc.practice.exception.ResourceConflictException;
import tw.edu.ntub.imd.birc.practice.exception.ResourceNotFoundException;
import tw.edu.ntub.imd.birc.practice.exception.form.InvalidFormException;
import tw.edu.ntub.imd.birc.practice.service.RecordService;
import tw.edu.ntub.imd.birc.practice.service.transformer.impl.RecordsTransformerImpl;
import tw.edu.ntub.birc.common.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class RecordServiceImpl extends BaseServiceImpl<RecordsBean, Records, Integer> implements RecordService {
    private final RecordsDAO recordsDAO;
    private final RecordsTransformerImpl recordsTransformer;
    private final RentDAO rentDAO;

    public RecordServiceImpl(RecordsDAO dao, RecordsTransformerImpl transformer, RentDAO rentDAO) {
        super(dao, transformer);
        this.recordsDAO = dao;
        this.recordsTransformer = transformer;
        this.rentDAO = rentDAO;
    }

    @Override
    public RecordsBean borrow(RecordsBean bean) {
        Rent rent = rentDAO.findById(bean.getRentId())
                .orElseThrow(() -> new ResourceNotFoundException("物品不存在"));
        if (rent.getUserId().equals(bean.getUserId()))
            throw new InvalidFormException("不能借用自己分享的物品");
        if (bean.getDueDate() == null || bean.getDueDate().isBefore(LocalDateTime.now()))
            throw new InvalidFormException("預計歸還日必須晚於現在");

        // 原子搶借：改到 1→0 的人才算借到，其餘擋掉
        if (rentDAO.markBorrowed(bean.getRentId()) == 0)
            throw new ResourceConflictException("此物品已被借出");

        LocalDateTime now = LocalDateTime.now();
        Records r = new Records();
        r.setRentId(bean.getRentId());
        r.setUserId(bean.getUserId());
        r.setCategoryId(rent.getCategoryId());
        r.setEnable(true);                 // 借用中
        r.setReturnDate(bean.getDueDate());
        r.setCreateTime(now);
        return recordsTransformer.transferToBean(recordsDAO.save(r));
    }

    @Override
    public void returnItem(Integer rentId) {
        Records r = recordsDAO.findByRentIdAndEnableTrue(rentId)
                .orElseThrow(() -> new ResourceNotFoundException("此物品目前沒有借用中的紀錄"));
        r.setEnable(false);                        // 已歸還
        r.setModifyTime(LocalDateTime.now());      // 實際歸還時間
        recordsDAO.save(r);
        rentDAO.markAvailable(rentId);             // 物品重新上架
    }

    @Override
    public List<RecordsBean> searchOverdue() {
        return CollectionUtils.map(
                recordsDAO.findByEnableTrueAndReturnDateBefore(LocalDateTime.now()),
                recordsTransformer::transferToBean);
    }

    @Override
    public Map<String, Object> statistics() {
        Map<String, Object> m = new LinkedHashMap<>();
        m.put("totalBorrowCount", recordsDAO.count());        // 累積借用次數
        m.put("activeBorrowCount", recordsDAO.countByEnableTrue());
        m.put("returnedCount", recordsDAO.countByEnableFalse());
        m.put("overdueCount", searchOverdue().size());
        return m;
    }

    @Override
    public RecordsBean save(RecordsBean bean) {
        // 借用就是新增一筆借用紀錄，直接複用 borrow 的完整邏輯
        return borrow(bean);
    }

    @Override
    public void update(Integer id, RecordsBean bean) {
        Records r = recordsDAO.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("借用紀錄不存在"));
        if (bean.getDueDate() != null) {
            r.setReturnDate(bean.getDueDate());   // 只允許改預計歸還日
        }
        r.setModifyTime(LocalDateTime.now());
        recordsDAO.save(r);
    }
}
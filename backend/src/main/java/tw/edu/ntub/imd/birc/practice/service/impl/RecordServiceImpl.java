package tw.edu.ntub.imd.birc.practice.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import tw.edu.ntub.imd.birc.practice.bean.RecordsBean;
import tw.edu.ntub.imd.birc.practice.databaseconfig.dao.RecordsDAO;
import tw.edu.ntub.imd.birc.practice.databaseconfig.dao.RentDAO;
import tw.edu.ntub.imd.birc.practice.databaseconfig.dao.UserDAO;
import tw.edu.ntub.imd.birc.practice.databaseconfig.entity.Records;
import tw.edu.ntub.imd.birc.practice.databaseconfig.entity.Rent;
import tw.edu.ntub.imd.birc.practice.databaseconfig.entity.User;
import tw.edu.ntub.imd.birc.practice.exception.ResourceConflictException;
import tw.edu.ntub.imd.birc.practice.exception.ResourceNotFoundException;
import tw.edu.ntub.imd.birc.practice.exception.form.InvalidFormException;
import tw.edu.ntub.imd.birc.practice.service.RecordService;
import tw.edu.ntub.imd.birc.practice.service.transformer.impl.RecordsTransformerImpl;
import tw.edu.ntub.birc.common.util.CollectionUtils;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class RecordServiceImpl extends BaseServiceImpl<RecordsBean, Records, Integer> implements RecordService {
    private final RecordsDAO recordsDAO;
    private final RecordsTransformerImpl recordsTransformer;
    private final RentDAO rentDAO;
    private final UserDAO userDAO;

    public RecordServiceImpl(RecordsDAO dao, RecordsTransformerImpl transformer, RentDAO rentDAO, UserDAO userDAO) {
        super(dao, transformer);
        this.recordsDAO = dao;
        this.recordsTransformer = transformer;
        this.rentDAO = rentDAO;
        this.userDAO = userDAO;
    }

    @Transactional
    @Override
    public RecordsBean borrow(RecordsBean bean) {
        Rent rent = rentDAO.findById(bean.getRentId())
                .orElseThrow(() -> new ResourceNotFoundException("物品不存在"));

        // 借用人 = 登入者
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userDAO.findByAccountName(auth.getName())
                .orElseThrow(() -> new ResourceNotFoundException("登入使用者不存在"));
        Integer borrowUserId = currentUser.getUserId();

        // 出借人 = 物品的物主（不看前端）
        Integer lendUserId = rent.getUserId();

        if (borrowUserId.equals(lendUserId))
            throw new InvalidFormException("不能借用自己分享的物品");
        if (bean.getDueDate() == null || bean.getDueDate().isBefore(LocalDateTime.now()))
            throw new InvalidFormException("預計歸還日必須晚於現在");

        if (rentDAO.markBorrowed(bean.getRentId()) == 0)
            throw new ResourceConflictException("此物品已被借出");

        Records r = new Records();
        r.setRentId(bean.getRentId());
        r.setLendUserId(lendUserId);
        r.setBorrowUserId(borrowUserId);
        r.setEnable(true);
        r.setReturnDate(bean.getDueDate());
        r.setCreateTime(LocalDateTime.now());
        return recordsTransformer.transferToBean(recordsDAO.save(r));
    }

    @Override
    public void returnItem(Integer rentId) {
        Records r = recordsDAO.findByRentIdAndEnableTrue(rentId)
                .orElseThrow(() -> new ResourceNotFoundException("此物品目前沒有借用中的紀錄"));
        r.setEnable(false);           // 已歸還（無 modify_time 可記實際時間）
        recordsDAO.save(r);
        rentDAO.markAvailable(rentId);
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

    @Transactional
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
        recordsDAO.save(r);
    }
}
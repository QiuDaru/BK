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

        // 檢查可借 → 改旗標 → save()（save 自帶交易，避開 TransactionRequiredException）
        if (!Boolean.TRUE.equals(rent.getRentEnable()))
            throw new ResourceConflictException("此物品已被借出");
        rent.setRentEnable(false);
        rentDAO.save(rent);

        Records r = new Records();
        r.setRentId(bean.getRentId());
        r.setLendUserId(lendUserId);
        r.setBorrowUserId(borrowUserId);
        r.setEnable(true);
        r.setReturnDate(bean.getDueDate());
        r.setCreateTime(LocalDateTime.now());
        r.setBorrowPhotoLink(bean.getBorrowPhotoLink());
        return recordsTransformer.transferToBean(recordsDAO.save(r));
    }

    @Override
    public void returnItem(Integer rentId, String returnPhotoLink) {
        Records r = recordsDAO.findByRentIdAndEnableTrue(rentId)
                .orElseThrow(() -> new ResourceNotFoundException("此物品目前沒有借用中的紀錄"));
        r.setEnable(false);
        r.setReturnPhotoLink(returnPhotoLink);   // 歸還照
        recordsDAO.save(r);

        Rent rent = rentDAO.findById(rentId)
                .orElseThrow(() -> new ResourceNotFoundException("物品不存在"));
        rent.setRentEnable(true);
        rentDAO.save(rent);
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
        m.put("totalBorrowCount", recordsDAO.count());
        m.put("activeBorrowCount", recordsDAO.countByEnableTrue());
        m.put("returnedCount", recordsDAO.countByEnableFalse());
        m.put("overdueCount", searchOverdue().size());
        return m;
    }

    @Override
    public RecordsBean save(RecordsBean bean) {
        return borrow(bean);
    }

    @Override
    public void update(Integer id, RecordsBean bean) {
        Records r = recordsDAO.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("借用紀錄不存在"));
        if (bean.getDueDate() != null) {
            r.setReturnDate(bean.getDueDate());
        }
        recordsDAO.save(r);
    }
}
package tw.edu.ntub.imd.birc.practice.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import tw.edu.ntub.imd.birc.practice.bean.RentBean;
import tw.edu.ntub.imd.birc.practice.databaseconfig.dao.RentDAO;
import tw.edu.ntub.imd.birc.practice.databaseconfig.dao.UserDAO;
import tw.edu.ntub.imd.birc.practice.databaseconfig.entity.Rent;
import tw.edu.ntub.imd.birc.practice.databaseconfig.entity.User;
import tw.edu.ntub.imd.birc.practice.exception.ResourceNotFoundException;
import tw.edu.ntub.imd.birc.practice.service.RentService;
import tw.edu.ntub.birc.common.util.CollectionUtils;
import tw.edu.ntub.imd.birc.practice.service.transformer.impl.RentTransformerImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RentServiceImpl extends BaseServiceImpl<RentBean, Rent, Integer> implements RentService {
    private final RentDAO rentDAO;
    private final RentTransformerImpl rentTransformer;
    private final UserDAO userDAO;

    public RentServiceImpl(RentDAO dao, RentTransformerImpl transformer, UserDAO userDAO) {
        super(dao, transformer);
        this.rentDAO = dao;
        this.rentTransformer = transformer;
        this.userDAO = userDAO;
    }

    @Override
    public RentBean save(RentBean bean) {
        Rent rent = rentTransformer.transferToEntity(bean);
        rent.setRentId(null);                 // 確保是新增
        rent.setRentEnable(true);             // 預設上架
        rent.setCreateTime(LocalDateTime.now());
        rent.setModifyTime(null);

        // 物主 = 登入者（從 token 取得，不信任前端）
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User owner = userDAO.findByAccountName(auth.getName())
                .orElseThrow(() -> new ResourceNotFoundException("登入使用者不存在"));
        rent.setUserId(owner.getUserId());

        rent = rentDAO.save(rent);
        return rentTransformer.transferToBean(rent);
    }

    @Override
    public void update(Integer id, RentBean bean) {
        Rent rent = rentDAO.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("物品不存在"));
        if (bean.getYear() != null)       rent.setYear(bean.getYear());
        if (bean.getCategoryId() != null) rent.setCategoryId(bean.getCategoryId());
        if (bean.getItem() != null)       rent.setItem(bean.getItem());
        if (bean.getPhotoLink() != null)  rent.setPhotoLink(bean.getPhotoLink());
        if (bean.getRemark() != null)     rent.setRemark(bean.getRemark());
        rent.setModifyTime(LocalDateTime.now());
        rentDAO.save(rent);
    }

    @Override
    public Optional<RentBean> getById(Integer id) {
        return rentDAO.findWithRelationsByRentId(id).map(rentTransformer::transferToBean);
    }

    @Override
    public List<RentBean> searchAvailable() {
        return CollectionUtils.map(
                rentDAO.findByRentEnableTrueOrderByCreateTimeDesc(),
                rentTransformer::transferToBean);
    }

    @Override
    public void updateEnable(Integer id, boolean enable) {
        Rent rent = rentDAO.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("物品不存在"));
        rent.setRentEnable(enable);
        rent.setModifyTime(LocalDateTime.now());
        rentDAO.save(rent);
    }
}
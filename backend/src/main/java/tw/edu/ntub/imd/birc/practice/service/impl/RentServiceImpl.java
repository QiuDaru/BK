package tw.edu.ntub.imd.birc.practice.service.impl;

import org.springframework.stereotype.Service;
import tw.edu.ntub.imd.birc.practice.bean.RentBean;
import tw.edu.ntub.imd.birc.practice.databaseconfig.dao.RentDAO;
import tw.edu.ntub.imd.birc.practice.databaseconfig.entity.Rent;
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

    public RentServiceImpl(RentDAO dao, RentTransformerImpl transformer) {
        super(dao, transformer);
        this.rentDAO = dao;
        this.rentTransformer = transformer;
    }

    @Override
    public RentBean save(RentBean bean) {
        Rent rent = rentTransformer.transferToEntity(bean);
        rent.setRentId(null);                 // 確保是新增
        rent.setRentEnable(true);             // 預設上架
        rent.setCreateTime(LocalDateTime.now());
        rent.setModifyTime(null);
        rent = rentDAO.save(rent);
        return rentTransformer.transferToBean(rent);
    }

    @Override
    public void update(Integer id, RentBean bean) {
        Rent rent = rentDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("物品不存在"));
        if (bean.getYear() != null)       rent.setYear(bean.getYear());
        if (bean.getCategoryId() != null) rent.setCategoryId(bean.getCategoryId());
        if (bean.getItem() != null)       rent.setItem(bean.getItem());
        if (bean.getPhotoId() != null)    rent.setPhotoId(bean.getPhotoId());
        if (bean.getRemark() != null)     rent.setRemark(bean.getRemark());
        rent.setModifyTime(LocalDateTime.now());
        rentDAO.save(rent);
    }

    // 覆寫 getById：用 EntityGraph 版本，避免 lazy 關聯讀不到
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
                .orElseThrow(() -> new RuntimeException("物品不存在"));
        rent.setRentEnable(enable);
        rent.setModifyTime(LocalDateTime.now());
        rentDAO.save(rent);
    }
}
package tw.edu.ntub.imd.birc.practice.service;

import tw.edu.ntub.imd.birc.practice.bean.RentBean;
import java.util.List;

public interface RentService extends BaseService<RentBean, Integer> {
    List<RentBean> searchAvailable();          // 只回可借（前端清單）
    void updateEnable(Integer id, boolean enable);   // 上/下架
}
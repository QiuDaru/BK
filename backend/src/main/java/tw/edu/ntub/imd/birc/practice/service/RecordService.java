package tw.edu.ntub.imd.birc.practice.service;

import tw.edu.ntub.imd.birc.practice.bean.RecordsBean;
import java.util.List;
import java.util.Map;

public interface RecordService extends BaseService<RecordsBean, Integer> {
    RecordsBean borrow(RecordsBean bean);       // 借用
    void returnItem(Integer rentId,String returnPhotoLink);            // 歸還
    List<RecordsBean> searchOverdue();          // 逾期清單
    Map<String, Object> statistics();           // 借用統計
}
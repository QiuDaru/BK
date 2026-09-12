package tw.edu.ntub.imd.birc.practice.databaseconfig.dao;

import tw.edu.ntub.imd.birc.practice.databaseconfig.entity.Records;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface RecordsDAO extends BaseDAO<Records, Integer> {
    Optional<Records> findByRentIdAndEnableTrue(Integer rentId);          // 找借用中那筆
    List<Records> findByEnableTrueAndReturnDateBefore(LocalDateTime now); // 逾期
    long countByEnableTrue();   // 借用中
    long countByEnableFalse();  // 已歸還
}
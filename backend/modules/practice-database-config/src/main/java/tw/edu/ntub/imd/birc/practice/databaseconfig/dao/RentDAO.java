package tw.edu.ntub.imd.birc.practice.databaseconfig.dao;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import tw.edu.ntub.imd.birc.practice.databaseconfig.entity.Rent;
import java.util.List;
import java.util.Optional;

public interface RentDAO extends BaseDAO<Rent, Integer> {

    @EntityGraph(attributePaths = {"category", "photo", "user"})
    List<Rent> findByRentEnableTrueOrderByCreateTimeDesc();

    @EntityGraph(attributePaths = {"category", "photo", "user"})
    Optional<Rent> findWithRelationsByRentId(Integer rentId);
    // 只有成功把 1 改成 0 的請求才算借到 → 擋住同時借
    @Modifying(clearAutomatically = true)
    @Query("update Rent r set r.rentEnable = false where r.rentId = :id and r.rentEnable = true")
    int markBorrowed(Integer id);

    @Modifying(clearAutomatically = true)
    @Query("update Rent r set r.rentEnable = true where r.rentId = :id")
    int markAvailable(Integer id);
}
package tw.edu.ntub.imd.birc.practice.databaseconfig.dao;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tw.edu.ntub.imd.birc.practice.databaseconfig.entity.Rent;
import java.util.List;
import java.util.Optional;

public interface RentDAO extends BaseDAO<Rent, Integer> {
    @EntityGraph(attributePaths = {"category", "user"})
    List<Rent> findByRentEnableTrueOrderByCreateTimeDesc();

    @EntityGraph(attributePaths = {"category", "user"})
    Optional<Rent> findWithRelationsByRentId(Integer rentId);

    @Modifying(clearAutomatically = true)
    @Query("update Rent r set r.rentEnable = false where r.rentId = :id and r.rentEnable = true")
    int markBorrowed(@Param("id") Integer id);

    @Modifying(clearAutomatically = true)
    @Query("update Rent r set r.rentEnable = true where r.rentId = :id")
    int markAvailable(@Param("id") Integer id);
}
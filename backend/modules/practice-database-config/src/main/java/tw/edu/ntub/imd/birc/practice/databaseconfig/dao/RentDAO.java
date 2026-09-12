package tw.edu.ntub.imd.birc.practice.databaseconfig.dao;

import org.springframework.data.jpa.repository.EntityGraph;
import tw.edu.ntub.imd.birc.practice.databaseconfig.entity.Rent;
import java.util.List;
import java.util.Optional;

public interface RentDAO extends BaseDAO<Rent, Integer> {

    @EntityGraph(attributePaths = {"category", "photo", "user"})
    List<Rent> findByRentEnableTrueOrderByCreateTimeDesc();

    @EntityGraph(attributePaths = {"category", "photo", "user"})
    Optional<Rent> findWithRelationsByRentId(Integer rentId);
}
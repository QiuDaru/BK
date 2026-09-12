package tw.edu.ntub.imd.birc.practice.databaseconfig.dao;

import org.springframework.stereotype.Repository;
import tw.edu.ntub.imd.birc.practice.databaseconfig.entity.Item;

@Repository
public interface ItemDAO extends BaseDAO<Item,Integer> {
}

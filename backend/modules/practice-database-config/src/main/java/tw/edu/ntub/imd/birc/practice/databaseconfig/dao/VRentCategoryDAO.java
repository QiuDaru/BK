package tw.edu.ntub.imd.birc.practice.databaseconfig.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import tw.edu.ntub.imd.birc.practice.databaseconfig.entity.VRentCategory;

public interface VRentCategoryDAO extends JpaRepository<VRentCategory, String> {
}
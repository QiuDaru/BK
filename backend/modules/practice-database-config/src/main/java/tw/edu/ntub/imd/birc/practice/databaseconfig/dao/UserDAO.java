package tw.edu.ntub.imd.birc.practice.databaseconfig.dao;

import tw.edu.ntub.imd.birc.practice.databaseconfig.entity.User;

import java.util.Optional;

public interface UserDAO  extends BaseDAO<User, Integer> {
    Optional<User> findByAccountName(String accountName);
}

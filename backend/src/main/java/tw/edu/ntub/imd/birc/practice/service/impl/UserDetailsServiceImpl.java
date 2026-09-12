package tw.edu.ntub.imd.birc.practice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tw.edu.ntub.imd.birc.practice.databaseconfig.dao.UserDAO;
import tw.edu.ntub.imd.birc.practice.databaseconfig.entity.User;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String accountName) throws UsernameNotFoundException {
        User u = userDAO.findByAccountName(accountName)
                .orElseThrow(() -> new UsernameNotFoundException("帳號或密碼錯誤"));
        return org.springframework.security.core.userdetails.User.builder()
                .username(u.getAccountName())
                .password(u.getPassword())
                .authorities(Collections.singletonList(
                        new SimpleGrantedAuthority("ROLE_" + u.getRoleId())))
                .disabled(!Boolean.TRUE.equals(u.getEnable()))
                .build();
    }
}
package tw.edu.ntub.imd.birc.practice.config.provider;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public CustomAuthenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        setHideUserNotFoundExceptions(false);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails,
                                                  UsernamePasswordAuthenticationToken authentication) {
        if (authentication.getCredentials() == null)
            throw new BadCredentialsException("請輸入密碼");
        String rawPassword = authentication.getCredentials().toString();
        if (!passwordEncoder.matches(rawPassword, userDetails.getPassword()))
            throw new BadCredentialsException("帳號或密碼錯誤");
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(authentication.getName());
        if (!userDetails.isEnabled())
            throw new AuthenticationServiceException("您的帳號已被停權");
        return userDetails;
    }
}
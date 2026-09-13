package tw.edu.ntub.imd.birc.practice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import tw.edu.ntub.imd.birc.practice.config.entrypoint.CustomEntryPoint;
import tw.edu.ntub.imd.birc.practice.config.filter.CustomLoginFilter;
import tw.edu.ntub.imd.birc.practice.config.filter.JwtAuthenticationFilter;
import tw.edu.ntub.imd.birc.practice.config.handler.CustomAuthenticationSuccessHandler;
import tw.edu.ntub.imd.birc.practice.config.handler.CustomLogoutSuccessHandler;
import tw.edu.ntub.imd.birc.practice.config.handler.CustomerAccessDeniedHandler;
import tw.edu.ntub.imd.birc.practice.config.provider.CustomAuthenticationProvider;
import tw.edu.ntub.imd.birc.practice.config.provider.properties.FileProperties;
import tw.edu.ntub.imd.birc.practice.config.provider.properties.ImageProperties;

import java.util.Arrays;
import java.util.Collections;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final String imageUrlName;
    private final String fileUrlName;
    private final UserDetailsService userDetailsService;
    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    public SecurityConfig(
            FileProperties fileProperties,
            ImageProperties imageProperties,
            UserDetailsService userDetailsService,
            CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler,
            JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.fileUrlName = fileProperties.getName();
        this.imageUrlName = imageProperties.getName();
        this.userDetailsService = userDetailsService;
        this.customAuthenticationSuccessHandler = customAuthenticationSuccessHandler;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(new CustomAuthenticationProvider(userDetailsService, passwordEncoder()));
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers(
                        HttpMethod.GET,
                        "/doc/**",
                        "/api/**",
                        "/v3/**",
                        "/swagger-ui/**",
                        "/swagger-ui.html",
                        "/csrf",
                        "/webjars/**",
                        "/v2/**",
                        "/swagger-resources/**",
                        "/favicon.ico",
                        "/static/**",
                        "/excel/test",
                        String.format("/%s/**", imageUrlName),
                        String.format("/%s/**", fileUrlName)
                );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(new CustomEntryPoint())
                .accessDeniedHandler(new CustomerAccessDeniedHandler())
                .and()
                .cors()
                .configurationSource(corsConfigurationSource())
                .and()
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(
                        new CustomLoginFilter(authenticationManagerBean(), customAuthenticationSuccessHandler),
                        UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                // @TODO Demo 測完登入後，改用下方 authenticated 版本鎖 API
                 .antMatchers("/login").permitAll()
                 .antMatchers(HttpMethod.GET, "/rents", "/rents/**").permitAll()
                 .antMatchers(HttpMethod.GET, "/stats/**").permitAll()
                 .anyRequest().authenticated()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
                .logoutSuccessHandler(new CustomLogoutSuccessHandler())
                .deleteCookies("JSESSIONID")
                .and()
                .sessionManagement()
                .sessionFixation()
                .migrateSession()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        ;
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Collections.singletonList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Collections.singletonList("*"));
        configuration.setExposedHeaders(Collections.singletonList("X-Auth-Token"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
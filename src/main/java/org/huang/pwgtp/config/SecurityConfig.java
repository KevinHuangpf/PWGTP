package org.huang.pwgtp.config;

import org.huang.pwgtp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Autowired
    private UserService userService;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeHttpRequests(request -> request
                        .requestMatchers(
                                "/user/login",
                                "/user/register",
                                "/user/logout",
                                "/login.html"
                        ).permitAll()
                        .requestMatchers(
                                "/v3/api-docs/*",
                                "/v3/api-docs",
                                "/webjars/**",
                                "/doc.html"
                        ).permitAll()
                        .anyRequest().authenticated()
                  )
                .formLogin(login -> login.loginPage("/login.html").defaultSuccessUrl("/index.html",true))
                .logout(logout -> logout.logoutUrl("/user/logout").logoutSuccessUrl("/login.html").permitAll())
                .csrf(AbstractHttpConfigurer::disable);
        return httpSecurity.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userService.loadUserByUsername(username);
    }
}

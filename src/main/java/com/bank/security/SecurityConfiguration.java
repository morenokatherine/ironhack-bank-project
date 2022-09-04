package com.bank.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration {

    @Bean
    PasswordEncoder passwordEncoder() {return new BCryptPasswordEncoder(); }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConf) throws Exception {
        return authConf.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.httpBasic();
        http.authorizeHttpRequests((authz) -> authz
            .mvcMatchers(HttpMethod.POST, "/users/admin").hasRole("ADMIN")
            .mvcMatchers(HttpMethod.POST, "/users/account-holder").hasRole("ADMIN")
            .mvcMatchers(HttpMethod.POST, "/users/third-party").hasRole("ADMIN")
            .mvcMatchers(HttpMethod.GET, "/accounts").hasAnyRole("ACCOUNT_HOLDER","ADMIN")
            .mvcMatchers(HttpMethod.GET, "/accounts/{accountId}").hasAnyRole("ACCOUNT_HOLDER","ADMIN")
            .mvcMatchers(HttpMethod.PATCH, "/accounts/{accountId}").hasRole("ADMIN")
            .mvcMatchers(HttpMethod.POST, "/accounts/savings").hasRole("ADMIN")
            .mvcMatchers(HttpMethod.POST, "/accounts/checkings").hasRole("ADMIN")
            .mvcMatchers(HttpMethod.POST, "/accounts/credit-cards").hasRole("ADMIN")
            .mvcMatchers(HttpMethod.POST, "/transfers").hasRole("ACCOUNT_HOLDER")
            .mvcMatchers(HttpMethod.POST, "/transfers/external/send").permitAll()
            .mvcMatchers(HttpMethod.POST, "/transfers/external/receive").permitAll()
            .anyRequest().permitAll()
        );

        http.csrf().disable();
        return http.build();
    }

}
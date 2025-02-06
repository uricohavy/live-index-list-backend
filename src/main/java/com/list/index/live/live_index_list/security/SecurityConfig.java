package com.list.index.live.live_index_list.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .anyRequest().permitAll() //for testing only
//                                .requestMatchers("/users/signup", "/users/add").permitAll()  // No authentication needed for signup or adding user
//                                .anyRequest().authenticated()  // All other requests require authentication
                )
                //.httpBasic();  // For basic authentication (you can configure other authentication mechanisms here)
        .csrf().disable()// Disable CSRF for non-browser requests
        .cors().disable();
        return http.build();
    }
}

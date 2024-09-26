package com.modul295_lb1.productmanager.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtRequestFilterNew jwtRequestFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        http.csrf().disable();

        http
                .httpBasic().disable()
                .authorizeHttpRequests((authz) ->
                        authz.requestMatchers("/users/register").permitAll()
                                .requestMatchers("/users/login").permitAll()
                                .requestMatchers("/products").hasAuthority("ROLE_ADMIN")
                                .requestMatchers("/products/**").permitAll()
                                .requestMatchers("/categories/{id}/products").permitAll()
                                .requestMatchers("/categories/**").permitAll()
                                .requestMatchers("/swagger-ui/index.html").permitAll()
                                .requestMatchers("/swagger-ui/*").permitAll()
                                .requestMatchers("/v3/api-docs").permitAll()
                                .requestMatchers("/v3/api-docs/swagger-config").permitAll()
                                .anyRequest().authenticated()
                );
        return http.build();
    }
}

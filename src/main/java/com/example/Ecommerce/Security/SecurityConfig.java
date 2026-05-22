package com.example.Ecommerce.Security;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.config.Customizer;
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private CustomUserDetailService customUserDetailService;
    private JwtAuthFilter jwtAuthFilter;

    public SecurityConfig(CustomUserDetailService customUserDetailService, JwtAuthFilter jwtAuthFilter) {
        this.customUserDetailService = customUserDetailService;
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/auth/**",
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html"
                        ).permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.GET, "/products").hasAnyAuthority("USER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/orders").hasAuthority("USER")
                        .requestMatchers(HttpMethod.GET, "/orders/**").hasAuthority("USER")
                        .requestMatchers(HttpMethod.POST, "/products").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/products/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/products/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.POST,"/categories").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET,"/categories").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/categories/**").hasAnyAuthority("ADMIN", "USER")
                        .requestMatchers(HttpMethod.PUT,"/orders/*/cancel").hasAuthority("USER")
                        .requestMatchers(HttpMethod.PUT,"/orders/*").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET,"/products/**").hasAnyAuthority("ADMIN", "USER")
                        .requestMatchers(HttpMethod.GET, "/products/*/name").hasAnyAuthority("ADMIN", "USER")
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(customUserDetailService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}

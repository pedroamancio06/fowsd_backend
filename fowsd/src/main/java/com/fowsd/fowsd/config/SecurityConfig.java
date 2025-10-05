package com.fowsd.fowsd.config;

import com.fowsd.fowsd.security.JwtFilter;
import com.fowsd.fowsd.security.UserDetailsServiceImpl;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
    private final JwtFilter jwtFilter;
    private final UserDetailsServiceImpl userDetailsService;

    public SecurityConfig(JwtFilter jwtFilter, UserDetailsServiceImpl userDetailsService) {
        this.jwtFilter = jwtFilter; this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeHttpRequests(auth -> auth
                    // PERMITE ENDPOINTS PÚBLICOS (Swagger e Login)
                    .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html", "/actuator/**").permitAll()
                    .requestMatchers("/api/auth/**").permitAll() 
                    
                    // --- O BLOCO QUE VOCÊ DEVE ADICIONAR VEM AQUI ---
                    
                    // Exemplo 1: Se o Dashboard for público (NÃO RECOMENDADO, mas libera o acesso)
                    // .requestMatchers("/api/v1/dashboard", "/api/v1/leituras/sensor/**").permitAll()
                    
                    // Se você NÃO quer que sejam públicos, não precisa adicionar nada aqui!
                    
                    // TUDO MAIS EXIGE AUTENTICAÇÃO (cart, orders, /api/v1/dashboard, /api/v1/leituras/...)
                    .anyRequest().authenticated()
            )
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
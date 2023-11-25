package com.example.uniamerica.pizzaria.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http
                .cors(cors -> cors.disable())
                .csrf(csrf -> csrf.disable())
                        .oauth2ResourceServer(
                            oauth2 -> oauth2
                                    .jwt(jwt -> jwt.jwtAuthenticationConverter(new JWTConverter()))
                        )
                .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/*", "/auth")
                .permitAll()
                .anyRequest()
                .authenticated());

        http.httpBasic(Customizer.withDefaults());
        return http.build();
    }
}

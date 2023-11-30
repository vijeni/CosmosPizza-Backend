package com.example.uniamerica.pizzaria.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.web.filter.ForwardedHeaderFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.disable())
                .csrf(csrf -> csrf.disable())
                .oauth2ResourceServer(
                        oauth2 -> oauth2
                                .jwt(jwt -> jwt.jwtAuthenticationConverter(new JWTConverter()))
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .requestMatchers("/auth/*", "/auth")
                        .permitAll()
                        .requestMatchers("/cosmospizza/backend/**").permitAll() // Adicione esta linha para as requisições do backend
                        .anyRequest().authenticated());

        http
                .httpBasic(Customizer.withDefaults())
                .headers(headers -> headers
                        .frameOptions().disable()
                        .httpStrictTransportSecurity().disable()
                );

        // Configuração para permitir cabeçalhos do proxy
        http.addFilterBefore(new ForwardedHeaderFilter(), AbstractPreAuthenticatedProcessingFilter.class);

        return http.build();
    }
}


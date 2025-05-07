package es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.config;

import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.filters.JwtAutenticationFilter;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.services.users.AppUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@Order(200)
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain webSecurityFilterChain(HttpSecurity http, JwtAutenticationFilter jwtFilter) throws Exception {

        http.formLogin(Customizer.withDefaults());
        http.authorizeHttpRequests(auth -> auth

                .anyRequest().permitAll()
        );

        return http.build();
    }
}


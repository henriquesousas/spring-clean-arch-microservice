package br.com.opinai.api.conta.domain.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


//        http
//                .csrf().disable()
//                .authorizeRequests()
//                // Libera o POST /users
//                .antMatchers(HttpMethod.POST, "/users").permitAll()
//                // Libera os GETs para /users e /users/{id}
//                .antMatchers(HttpMethod.GET, "/users/**").permitAll()
//                // Libera o PUT /users
//                .antMatchers(HttpMethod.PUT, "/users/**").permitAll()
//                .anyRequest().authenticated();

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .anyRequest().permitAll(); // Permite todas as requisições
        return http.build();
    }
}

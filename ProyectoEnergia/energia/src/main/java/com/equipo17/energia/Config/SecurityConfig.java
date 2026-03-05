package com.equipo17.energia.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
        .csrf(csrf -> csrf.disable()) // si no se desactiva falla POST/PUT/DELETE
        .authorizeHttpRequests(auth -> auth
        .requestMatchers("/api/users/{id}").permitAll()
         .requestMatchers("/api/users").permitAll()
        .requestMatchers("/api/users/login").permitAll()
         .requestMatchers("/api/country").permitAll()
         .requestMatchers("/api/region").permitAll()
         .requestMatchers("/api/company").permitAll() 
         .requestMatchers("/api/energy-type").permitAll()
         .requestMatchers("/api/power-plant").permitAll()
          .requestMatchers("/api/measurement-type").permitAll()
             .requestMatchers("/api/energy-record").permitAll()
        .anyRequest().authenticated())
        .formLogin(form -> form.disable())
        .httpBasic(basic -> basic.disable());
        return http.build(); 
          
    }
    
}

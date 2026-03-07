package com.equipo17.energia.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher; // Importante para matchers específicos

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                // 1. Deshabilitar CSRF (común en APIs Stateless)
                .csrf(csrf -> csrf.disable())

                // 2. Configurar gestión de sesiones
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                // 3. Autorización de rutas
                .authorizeHttpRequests(auth -> auth
                        // Permitir Swagger y documentación
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
                        // Permitir autenticación
                        .requestMatchers("/api/auth/**").permitAll()
                        // Roles específicos
                        .requestMatchers("/api/users/**").hasRole("ADMIN")
                        .requestMatchers("/api/energy/**").hasAnyRole("ADMIN", "USER", "ANALYST")
                        // Cualquier otra requiere autenticación
                        .anyRequest().authenticated()
                )

                // 4. Autenticación básica (para pruebas en Swagger)
                .httpBasic(Customizer.withDefaults())

                .build();
    }
}

/* import org.springframework.context.annotation.Bean;
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
 */
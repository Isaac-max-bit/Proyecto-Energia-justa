package com.equipo17.energia.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Permite usar "Admin" sin encriptar (solo para desarrollo)
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // 1. Deshabilitar CSRF para APIs
            .csrf(csrf -> csrf.disable())
            
            // 2. Activar CORS con la configuración que definimos abajo
            .cors(Customizer.withDefaults())

            // 3. Sesión sin estado (Stateless)
            .sessionManagement(session -> 
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )

            // 4. Autorización de rutas
            .authorizeHttpRequests(auth -> auth
                // Permitir que Angular haga el "pre-vuelo" (OPTIONS) sin autenticación
                .requestMatchers(org.springframework.http.HttpMethod.OPTIONS, "/**").permitAll()
                
                // Rutas públicas
                .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html", "/api/auth/**").permitAll()
                
                // Rutas protegidas: Solo pedimos que esté autenticado (sin importar el Rol por ahora)
                .requestMatchers("/api/energy/**").authenticated()
                .requestMatchers("/api/users/**").authenticated()
                
                .anyRequest().authenticated()
            )

            // 5. Habilitar Basic Auth para que Angular mande el usuario:clave
            .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    // Configuración detallada de CORS para que Angular pueda entrar
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200")); // Origen de Angular
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "Accept"));
        configuration.setAllowCredentials(true); // Permite el envío de credenciales
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
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
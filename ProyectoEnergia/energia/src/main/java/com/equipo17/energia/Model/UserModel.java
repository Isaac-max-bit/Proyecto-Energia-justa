package com.equipo17.energia.Model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data // Genera automáticamente Getters, Setters, toString, equals y hashCode
@NoArgsConstructor // Genera constructor vacío (obligatorio para JPA)
@AllArgsConstructor // Genera constructor con todos los campos
@Schema(description = "Modelo de usuario para el sistema de gestión de energía")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "ID autoincremental")
    private Long id;

    @Column(unique = true, nullable = false)
    @Schema(example = "admin@energy.com", description = "Email único que servirá como username")
    private String email;

    @Column(nullable = false)
    @Schema(description = "Contraseña encriptada del usuario")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Schema(example = "ADMIN", description = "Roles disponibles: ADMIN, USER, ANALYST")
    private Role role;

    // NOTA: He borrado los métodos manuales get/set porque @Data de Lombok los crea por ti.
}

    /* public enum Role {
        ADMIN, USER, ANALYST
    } */

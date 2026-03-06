package com.equipo17.energia.Repository;

//import com.equipo17.energia.Model.User;
import com.equipo17.energia.Model.UserModel;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    // Busco por email porque es unique campo en el modelo de la BD
    Optional<UserModel> findByEmail(String email);
}


/* public interface UserRepository extends JpaRepository<User, Long> {
    
    Optional<User> findByUsername(String username);
} */
package com.equipo17.energia.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.equipo17.energia.Model.UsuarioModel;

public interface UsuarioRepository extends JpaRepository<UsuarioModel,Long>{
    Optional<UsuarioModel> findByEmail(String email);
}

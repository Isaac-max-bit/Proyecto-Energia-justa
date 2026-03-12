package com.equipo17.energia.Service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.equipo17.energia.Model.UsuarioModel;
import com.equipo17.energia.Repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioModel crearUsuario(UsuarioModel user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return usuarioRepository.save(user);
    }

    public List<UsuarioModel> findAll(){
        return usuarioRepository.findAll(); 
    }
}

package com.equipo17.energia.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.equipo17.energia.Model.UsuarioModel;
import com.equipo17.energia.Service.UsuarioService;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioModel> create(@RequestBody UsuarioModel user){
        return ResponseEntity.ok(usuarioService.crearUsuario(user));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioModel>> findAll() {
        return ResponseEntity.ok(usuarioService.findAll());
    } 

}

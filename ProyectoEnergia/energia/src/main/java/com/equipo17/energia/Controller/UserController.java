package com.equipo17.energia.Controller;

import com.equipo17.energia.Model.UserModel;
import com.equipo17.energia.Service.UserService; //Service para encriptar la clave
import com.equipo17.energia.dto.LoginRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Tag(name = "Usuarios", description = "Gestión de usuarios y autenticación")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    // REGISTRO 
    @PostMapping("/register")
    @Operation(summary = "Registrar un nuevo usuario")
    public ResponseEntity<UserModel> register(@RequestBody UserModel user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.crearUsuario(user));
    }

    //LOGIN
    @PostMapping("/login")
    @Operation(summary = "Iniciar sesión")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(userService.login(request));
    }

    // OBTENER TODO
    @GetMapping
    @Operation(summary = "Obtener lista de todos los usuarios")
    public List<UserModel> getAll() {
        return userService.findAll();
    }
    //ELIMINA USUARIO
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un usuario")
    public String delete(@PathVariable Long id) {
        return "Usuario eliminado";
    }
}

/* import com.equipo17.energia.dto.LoginRequest;
import com.equipo17.energia.Model.User;
import com.equipo17.energia.Service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/users")

public class UserController {
    private final UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }
    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) { 
        return  ResponseEntity.status(HttpStatus.CREATED)
        .body(userService.crearUsuario(user));
    }

    @GetMapping
    public List<User> findAll(){
        return userService.findAll();
    }
    //READ BY ID
    @GetMapping("/{id}")
    public User findByID(@PathVariable Long id){
        return userService.findById(id)
        .orElseThrow(()-> new ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "Usuario no encontrado"));
    }
    // UPDATE
    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @RequestBody User userDetails){
        
        return userService.update(id, userDetails);

    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        String response = userService.login(request);
        return ResponseEntity.ok(response);
    }    
    
} */
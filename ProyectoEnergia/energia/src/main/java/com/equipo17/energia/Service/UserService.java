package com.equipo17.energia.Service;

//Se necesita completar

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

//import com.equipo17.energia.dto.LoginRequest;
import com.equipo17.energia.Model.User;
import com.equipo17.energia.Repository.UserRepository;
//import com.equipo17.energia.exception.ResourceNotFoundException;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
    }

    public User crearUsuario(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public Optional<User> findById(Long Id){
        return userRepository.findById(Id);
    }

    public User update(Long id, User userDetails){
         User user=userRepository.findById(id)
        .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Usuario no encontrado"));
        //Se puede así tambíen
        //.orElseThrow(()->new RuntimeException("Usuario no encontrado"));
        if(userDetails.getUsername()!=null && !userDetails.getUsername().trim().isEmpty()){
            user.setUsername(userDetails.getUsername());
        }
        if(userDetails.getEmail()!=null && !userDetails.getEmail().trim().isEmpty()){
            user.setEmail(userDetails.getEmail());
        }
        if(userDetails.getPassword()!=null && !userDetails.getPassword().trim().isEmpty()){
            user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
        }
        if(userDetails.getRole()!=null)
            user.setRole(userDetails.getRole());
        
        return userRepository.save(user);
    }

   /*  public String login(LoginRequest request){
        Optional<User> optionalUser=userRepository.findByUsername(request.getUsername());
        if(optionalUser.isEmpty()){
            throw new ResourceNotFoundException("Usuario no encontrado");
        }
        User user = optionalUser.get();
        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new ResourceNotFoundException("Contraseña incorrecta");
        }
        return "Login correcto";
    } */
}
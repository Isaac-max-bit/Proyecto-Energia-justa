package com.equipo17.energia.Repository;

import com.equipo17.energia.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
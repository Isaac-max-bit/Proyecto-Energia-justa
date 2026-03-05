package com.equipo17.energia.Repository;

import com.equipo17.energia.Model.EnergyType;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EnergyTypeRepository extends JpaRepository<EnergyType,Long>{
    
    Optional<EnergyType> findByName(String name);

    boolean existsByName(String name);

    List<EnergyType> findByRenewable(Boolean renewable);
}

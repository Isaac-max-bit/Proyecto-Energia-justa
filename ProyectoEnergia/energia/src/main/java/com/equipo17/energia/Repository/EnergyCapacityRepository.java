package com.equipo17.energia.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.equipo17.energia.Model.EnergyCapacity;

@Repository
public interface EnergyCapacityRepository extends JpaRepository<EnergyCapacity,Long>{
    
}

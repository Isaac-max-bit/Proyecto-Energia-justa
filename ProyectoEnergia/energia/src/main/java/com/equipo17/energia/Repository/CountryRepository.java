package com.equipo17.energia.Repository;

import com.equipo17.energia.Model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country,Long>{
    
}

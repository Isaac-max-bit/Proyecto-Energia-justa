package com.equipo17.energia.Repository;

import com.equipo17.energia.Model.Region;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<Region,Long>{
    List<Region> findByCountryId(Long countryId);

    // Validar región única por país
    Optional<Region> findByNameAndCountryId(String name, Long countryId);

    boolean existsByNameAndCountryId(String name, Long countryId);
}

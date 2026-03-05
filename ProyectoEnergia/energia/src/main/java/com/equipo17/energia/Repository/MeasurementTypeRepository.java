package com.equipo17.energia.Repository;

import com.equipo17.energia.Model.MeasurementType;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MeasurementTypeRepository extends JpaRepository<MeasurementType,Long>{

    Optional<MeasurementType> findByName(String name);

    boolean existsByName(String name);
}

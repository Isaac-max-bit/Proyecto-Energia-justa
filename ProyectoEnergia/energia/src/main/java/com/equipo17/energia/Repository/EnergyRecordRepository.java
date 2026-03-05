package com.equipo17.energia.Repository;

import com.equipo17.energia.Model.EnergyRecord;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EnergyRecordRepository extends JpaRepository<EnergyRecord,Long>{
    
    List<EnergyRecord> findByPowerPlantId(Long powerPlantId);

    List<EnergyRecord> findByYear(Integer year);

    boolean existsByPowerPlantIdAndYearAndMonthAndMeasurementTypeId(
            Long powerPlantId,
            Integer year,
            Integer month,
            Long measurementTypeId
    );
}

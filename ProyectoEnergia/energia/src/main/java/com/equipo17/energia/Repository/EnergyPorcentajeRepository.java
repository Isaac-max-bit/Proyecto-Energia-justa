package com.equipo17.energia.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.equipo17.energia.Model.EnergyPorcentaje;

@Repository
public interface EnergyPorcentajeRepository extends JpaRepository<EnergyPorcentaje,Long>{
    
    @Query("SELECT e.entity, e.renewablesSharePercent FROM EnergyPorcentaje e " +
           "WHERE e.dataYear = :year " +
           "AND (e.code IS NULL OR e.code = '' OR LENGTH(e.code) > 3) " +
           "AND e.entity NOT IN ('World', 'High-income countries', 'Low-income countries') " +
           "AND e.renewablesSharePercent IS NOT NULL")
    List<Object[]> findRenewablePercentageByYear(@Param("year") int year);

  /*   @Query("SELECT e.entity, e.renewablesPercentage FROM energy_porcentaje e " +
           "WHERE e.data_year = :year " +
           "AND (e.code IS NULL OR e.code = '' OR LENGTH(e.code) > 3) " +
           "AND e.entity NOT IN ('World', 'High-income countries', 'Low-income countries') " +
           "AND e.renewables_share_percent IS NOT NULL")
    List<Object[]> findRenewablePercentageByYear(@Param("year") int year); */

}

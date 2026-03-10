package com.equipo17.energia.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.equipo17.energia.Model.EnergyCapacity;

@Repository
public interface EnergyCapacityRepository extends JpaRepository<EnergyCapacity,Long>{

    @Query("SELECT e.dataYear, e.solarCapacityGw FROM EnergyCapacity e " +
           "WHERE e.entity = 'World' AND e.solarCapacityGw IS NOT NULL " +
           "ORDER BY e.dataYear ASC")
    List<Object[]> findGlobalSolarTrend();
    /* @Query("SELECT e.data_year, e.solar_capacity_gw FROM energy_capacity e " +
           "WHERE e.entity = 'World' AND e.solar_capacity_gw IS NOT NULL " +
           "ORDER BY e.data_year ASC")
    List<Object[]> findGlobalSolarTrend(); */
}

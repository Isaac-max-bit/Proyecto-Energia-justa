package com.equipo17.energia.Repository;

import com.equipo17.energia.Model.EnergyModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface EnergyRepository extends JpaRepository<EnergyModel, Long> {

    @Query("SELECT e.entity, e.solarTwh, e.windTwh, e.hydroTwh, e.otherRenewablesTwh " +
           "FROM EnergyModel e WHERE e.dataYear = :year " +
           "AND (e.code IS NULL OR e.code = '' OR LENGTH(e.code) > 3) " + 
           "AND e.entity NOT IN ('World', 'High-income countries', 'Low-income countries', 'Upper-middle-income countries', 'Lower-middle-income countries')")
    List<Object[]> findProductionBySourceAndYear(@Param("year") int year);

    @Query(value = "SELECT entity, windTwh FROM EnergyModel " +
                   "WHERE dataYear = :year AND LENGTH(code) = 3 " +
                   "AND windTwh IS NOT NULL " +
                   "ORDER BY windTwh DESC LIMIT 10", nativeQuery = true)
    List<Object[]> findTop10WindByYear(@Param("year") int year);

    // #5. Fuentes de energía y su participación en el consumo eléctrico total a nivel global.
    @Query("SELECT SUM(e.solarTwh), SUM(e.windTwh), SUM(e.hydroTwh), SUM(e.otherRenewablesTwh) " +
           "FROM EnergyModel e WHERE e.dataYear = :year AND LENGTH(e.code) = 3")
    List<Object[]> findGlobalParticipationByYear(@Param("year") int year);

   /*  @Query("SELECT e.entity, e.solar_twh, e.wind_twh, e.hydro_twh, e.other_renewables_twh " +
           "FROM energy_data e WHERE e.data_year = :year " +
           "AND (e.code IS NULL OR e.code = '' OR LENGTH(e.code) > 3) " + 
           "AND e.entity NOT IN ('World', 'High-income countries', 'Low-income countries', 'Upper-middle-income countries', 'Lower-middle-income countries')")
    List<Object[]> findProductionBySourceAndYear(@Param("year") int year);

    @Query(value = "SELECT entity, wind_twh FROM energy_data " +
                   "WHERE data_year = :year AND LENGTH(code) = 3 " +
                   "AND wind_twh IS NOT NULL " +
                   "ORDER BY wind_twh DESC LIMIT 10", nativeQuery = true)
    List<Object[]> findTop10WindByYear(@Param("year") int year);

    @Query("SELECT SUM(e.solar_twh), SUM(e.wind_twh), SUM(e.hydro_twh), SUM(e.other_renewables_twh) " +
           "FROM energy_data e WHERE e.data_year = :year AND LENGTH(e.code) = 3")
    List<Object[]> findGlobalParticipationByYear(@Param("year") int year); */
   
}
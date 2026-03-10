package com.equipo17.energia.Controller;

import com.equipo17.energia.Model.EnergyModel;
import com.equipo17.energia.Repository.EnergyRepository;
import com.equipo17.energia.Service.EnergyService;

import io.swagger.v3.oas.annotations.Operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/energy")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", allowCredentials = "true") // Pa que el Frontend no sea bloqueado por seguridad
public class EnergyController {

    @Autowired
    private EnergyRepository energyRepository;
    @Autowired
    private EnergyService energyService;

    // Obtene absolutamente todo
    @GetMapping("/all")
    public List<EnergyModel> getAllEnergyData() {
        return energyRepository.findAll();
    }

    @Operation(
        summary = "Consulta #1: Producción renovable por fuente", 
        description = "Retorna: region, solar_twh, wind_twh, hydro_twh, other_renewables_twh"
    )
    @GetMapping("/produccion")
    public List<Map<String, Object>> getProduction(@RequestParam int year) {
        return energyService.getProduction(year);
    }

    @Operation(
        summary = "Consulta #4: Top 10 países energía eólica", 
        description = "Retorna: pais, produccion_eolica_twh"
    )
    @GetMapping("/top-eolica")
    public List<Map<String, Object>> getTop10Wind(@RequestParam int year) {
        return energyService.getTop10Wind(year);
    }

    @Operation(
        summary = "Consulta #5: Participación global por fuentes", 
        description = "Retorna un solo objeto con los totales mundiales de Solar, Eólica e Hidro"
    )
    @GetMapping("/global-participacion")
    public Map<String, Object> getGlobalParticipation(@RequestParam int year) {
        return energyService.getGlobalParticipation(year);
    }
    /* // Busca por ID específico
    @GetMapping("/{id}")
    public ResponseEntity<EnergyModel> getEnergyById(@PathVariable Long id) {
        Optional<EnergyModel> data = energyRepository.findById(id);
        return data.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    } */

    /*
     * Producción total de energía renovable por tipo.
     * Esto le sirve al Front para hacer una gráficas DE TORTA
     */
   /*  @GetMapping("/global-stats/{year}")
    public List<Object[]> getGlobalStats(@PathVariable Integer year) {
        return energyRepository.findGlobalProductionByYear(year);
    } */

    /**
     * Top 10 países con mayor producción eólica.
     * Esto le sirve al Front para hacer una gráfica de Barras.
     */
 /*    @GetMapping("/top-wind/{year}")
    public List<Object[]> getTopWind(@PathVariable Integer year) {
        return energyRepository.findTop10WindProduction(year);
    } */

    /**
     * Tendencia histórica de capacidad solar.
     * Esto le sirve al Front para hacer una gráfica de Líneas
     */
 /*    @GetMapping("/solar-trend/{country}")
    public List<Object[]> getSolarTrend(@PathVariable String country) {
        return energyRepository.findSolarCapacityTrend(country);
    } */

    // Busca por País y Año exacto
    /* @GetMapping("/search")
    public ResponseEntity<EnergyModel> getByCountryAndYear(
            @RequestParam String entity,
            @RequestParam Integer year) {

        return energyRepository.findByEntityAndDataYear(entity, year)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    } */
}
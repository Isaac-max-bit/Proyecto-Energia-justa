package com.equipo17.energia.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.equipo17.energia.Service.EnergyCapacityService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/energy")
public class EnergyCapacityController {

    @Autowired
    private EnergyCapacityService capacityService;

    @Operation(
        summary = "Consulta #3: Tendencia de capacidad solar", 
        description = "Retorna: anio, capacidad_solar_global"
    )
    @GetMapping("/capacity")
    public List<Map<String, Object>> getSolarTrend() {
        return capacityService.getSolarTrend();
    }
}

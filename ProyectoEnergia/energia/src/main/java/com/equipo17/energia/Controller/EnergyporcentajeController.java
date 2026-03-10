package com.equipo17.energia.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.equipo17.energia.Service.EnergyPorcentajeService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/energy")
public class EnergyporcentajeController {

    @Autowired
    private EnergyPorcentajeService porcentajeService;

    @Operation(
        summary = "Consulta #2: Porcentaje de energía renovable", 
        description = "Retorna: region, porcentaje_renovable"
    )
    @GetMapping("/porcentaje")
    public List<Map<String, Object>> getPercentage(@RequestParam int year) {
        return porcentajeService.getPercentage(year);
    }
}

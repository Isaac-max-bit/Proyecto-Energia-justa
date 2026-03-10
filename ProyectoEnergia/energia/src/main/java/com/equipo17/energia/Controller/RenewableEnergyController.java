package com.equipo17.energia.Controller;

import com.equipo17.energia.Model.RenewableEnergy;
import com.equipo17.energia.Service.RenewableEnergyService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/energy")
// IMPORTANTE: Permitir que Angular (puerto 4200) acceda a estos datos
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", allowCredentials = "true")
public class RenewableEnergyController {

    private final RenewableEnergyService renewableEnergyService;

    // Inyección de dependencias por constructor
    public RenewableEnergyController(RenewableEnergyService renewableEnergyService) {
        this.renewableEnergyService = renewableEnergyService;
    }

    /**
     * Endpoint para obtener todos los datos procesados del CSV.
     * URL: http://localhost:8080/api/energy/renewables
     */
    @GetMapping("/renewables")
    public List<RenewableEnergy> getAllRenewableData() {
        return renewableEnergyService.getRenewableDataFromCsv();
    }
}

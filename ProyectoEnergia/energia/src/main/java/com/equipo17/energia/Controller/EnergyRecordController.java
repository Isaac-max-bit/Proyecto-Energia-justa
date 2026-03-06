/* package com.equipo17.energia.Controller;

import com.equipo17.energia.Service.EnergyRecordService;
import com.equipo17.energia.Model.EnergyRecord;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import java.util.List;

@RestController
@RequestMapping("/api/energy-record")
@RequiredArgsConstructor
public class EnergyRecordController {

    private final EnergyRecordService energyRecordService;

    @PostMapping
    public EnergyRecord create(@RequestBody EnergyRecord record) {
        return energyRecordService.save(record);
    }

    @GetMapping
    public List<EnergyRecord> findAll() {
        return energyRecordService.findAll();
    }

    @GetMapping("/{id}")
    public EnergyRecord findById(@PathVariable Long id) {
        return energyRecordService.findById(id);
    }

    @GetMapping("/plant/{plantId}")
    public List<EnergyRecord> findByPlant(@PathVariable Long plantId) {
        return energyRecordService.findByPlant(plantId);
    }
} */
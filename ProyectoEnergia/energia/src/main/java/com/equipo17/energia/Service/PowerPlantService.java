package com.equipo17.energia.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.equipo17.energia.Repository.*;
import com.equipo17.energia.Model.*;
import com.equipo17.energia.exception.ResourceNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PowerPlantService {

    private final PowerPlantRepository powerPlantRepository;
    private final CompanyRepository companyRepository;
    private final RegionRepository regionRepository;
    private final EnergyTypeRepository energyTypeRepository;

    public PowerPlant save(PowerPlant plant) {

        Long companyId = plant.getCompany().getId();
        Long regionId = plant.getRegion().getId();
        Long energyTypeId = plant.getEnergyType().getId();

        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new ResourceNotFoundException("Compañia no encontrada"));

        Region region = regionRepository.findById(regionId)
                .orElseThrow(() -> new ResourceNotFoundException("Región no encontrada"));

        EnergyType energyType = energyTypeRepository.findById(energyTypeId)
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de neergia no encontrado"));

        if (powerPlantRepository.existsByNameAndCompanyId(plant.getName(), companyId)) {
            throw new ResourceNotFoundException("Esta planta ya existe en esta compañia");
        }

        plant.setCompany(company);
        plant.setRegion(region);
        plant.setEnergyType(energyType);

        return powerPlantRepository.save(plant);
    }

    public List<PowerPlant> findAll() {
        return powerPlantRepository.findAll();
    }

    public PowerPlant findById(Long id) {
        return powerPlantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Planta no encontrada"));
    }

    public List<PowerPlant> findByCompany(Long companyId) {
        return powerPlantRepository.findByCompanyId(companyId);
    }

    public List<PowerPlant> findByRegion(Long regionId) {
        return powerPlantRepository.findByRegionId(regionId);
    }
}
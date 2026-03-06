/* package com.equipo17.energia.Service;

import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import com.equipo17.energia.Model.Region;
import com.equipo17.energia.Model.Country;
import com.equipo17.energia.Repository.RegionRepository;
import com.equipo17.energia.Repository.CountryRepository;

import com.equipo17.energia.exception.ResourceNotFoundException;

@Service
@RequiredArgsConstructor
public class RegionService {

    private final RegionRepository regionRepository;
    private final CountryRepository countryRepository;

    public Region save(Region region) {

        Long countryId = region.getCountry().getId();

        Country country = countryRepository.findById(countryId)
                .orElseThrow(() -> new ResourceNotFoundException("País no encontrado"));

        if (regionRepository.existsByNameAndCountryId(region.getName(), countryId)) {
            throw new ResourceNotFoundException("Esta región ya existe en este país");
        }

        region.setCountry(country);

        return regionRepository.save(region);
    }

    public List<Region> findAll() {
        return regionRepository.findAll();
    }

    public Region findById(Long id) {
        return regionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Región no encontrada"));
    }

    public List<Region> findByCountry(Long countryId) {
        return regionRepository.findByCountryId(countryId);
    }
} */
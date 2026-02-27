package com.equipo17.energia.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.equipo17.energia.Model.Region;
import com.equipo17.energia.Repository.RegionRepository;

@Service
public class RegionService {
    private final RegionRepository regionRepository;

    public RegionService(RegionRepository regionRepository){
        this.regionRepository=regionRepository;
    }

    public Region creaRegion(Region region){
        return regionRepository.save(region);
    }

    public List<Region> findAll(){
        return regionRepository.findAll();
    }

    public Optional<Region> findById(Long id){
        return regionRepository.findById(id);
    }

    
    //Las llaves foraneas no estan se necesita asesoria
    public Region update(Long id, Region regionDetails){
        Region region=regionRepository.findById(id)
        .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"region no encontrada"));

        if(regionDetails.getName()!=null && regionDetails.getName().trim().isEmpty()){
            region.setName(regionDetails.getName());
        }

        return regionRepository.save(region);


    }
}

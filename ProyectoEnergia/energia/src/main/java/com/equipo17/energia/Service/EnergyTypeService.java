package com.equipo17.energia.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.equipo17.energia.Model.EnergyType;
import com.equipo17.energia.Repository.EnergyTypeRepository;

@Service
public class EnergyTypeService {
    private final EnergyTypeRepository energyTypeRepository;

    public EnergyTypeService(EnergyTypeRepository energyTypeRepository){
        this.energyTypeRepository=energyTypeRepository;
    }

    public EnergyType creaEnergyType(EnergyType energyType){
        return energyTypeRepository.save(energyType);
    }

    public List<EnergyType> findAll(){
        return energyTypeRepository.findAll();
    }

    public Optional<EnergyType> findById(Long id){
        return energyTypeRepository.findById(id);
    }

    public EnergyType update(Long id, EnergyType energyTypeDetails){
        EnergyType energyType=energyTypeRepository.findById(id)
        .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Tipo de energia no encontrado"));

        if(energyTypeDetails.getName()!=null && energyTypeDetails.getName().trim().isEmpty()){
            energyType.setName(energyTypeDetails.getName());
        }

        if(energyTypeDetails.isRenewbable()==true){
            energyType.setRenewbable(true);
        }else{
            energyType.setRenewbable(false);
        }

        return energyTypeRepository.save(energyType);
    }
}

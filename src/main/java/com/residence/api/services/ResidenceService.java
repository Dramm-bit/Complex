package com.residence.api.services;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.residence.api.models.Residence;
import com.residence.api.repositories.ResidenceRepository;

@Service

public class ResidenceService {
    @Autowired
    private ResidenceRepository residenceRepository;

    public Optional <Residence> findResidenceById (Long id){

        return residenceRepository.findById(id);
        
        
    }
    
}

package com.residence.api.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residence.api.dataTranferObjects.ResidenceDTO;
import com.residence.api.models.PaymentConfig;
import com.residence.api.models.Residence;
import com.residence.api.repositories.ResidenceRepository;

@Service

public class ResidenceService {
    @Autowired
    private ResidenceRepository residenceRepository;

    /*
     * public Set<House> findResidenceById(Long id) {
     * 
     * List<House> finded = new ArrayList<>();
     * Optional<Residence> copy = residenceRepository.findById(id);// hacer una
     * exepcion que se encargue de dar una respuesta si no se encuentra el id
     * copy.get().getHouses().forEach(finded::add);
     * return finded;
     * 
     * }
     */

  }

    public List<Residence> getResidences() {
        
        return this.residenceRepository.findAll();
    }
    public Residence createResidence(ResidenceDTO residenceData){
        Residence newResidence = new Residence();

        PaymentConfig paymentConfig = new PaymentConfig();

        paymentConfig.setAmount(residenceData.getAmount());
        
        newResidence.setPaymentConfig(paymentConfig);
        newResidence.setAddress(residenceData.getAddress());
        newResidence.setName(residenceData.getName());
        newResidence.setPaymentConfig(newResidence.getPaymentConfig());
        newResidence = this.residenceRepository.save(newResidence);
        return newResidence;
    }


   /*  public Set<House> findResidenceById(Long id) {

        List<House> finded = new ArrayList<>();
        Optional<Residence> copy = residenceRepository.findById(id);// hacer una exepcion que se encargue de dar una respuesta si no se encuentra el id
        copy.get().getHouses().forEach(finded::add);
        return finded;

    } */

  
}

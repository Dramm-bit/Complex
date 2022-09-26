package com.residence.api.services;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.residence.api.dataTranferObjects.ResidenceDTO;
import com.residence.api.models.PaymentConfig;
import com.residence.api.models.Residence;
import com.residence.api.repositories.ResidenceRepository;

@Service

public class ResidenceService {
    @Autowired
    private ResidenceRepository residenceRepository;

    public List<Residence> getResidences() {

        return this.residenceRepository.findAll();
    }

    public Residence createResidence(ResidenceDTO residenceData) {
        if(residenceData.getAmount() == null || residenceData.getAddress() == null || residenceData.getName() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name, Address and Amount properties are required.");
        }
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
    public Residence updateResidence(ResidenceDTO residenceData, Long id) {
        Optional<Residence> queryResponse = this.residenceRepository.findById(id);
        if(queryResponse.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Residences not found");
        
        Residence residenceFound = queryResponse.get();
        if(residenceData.getAmount() != null) {
            
            residenceFound.getPaymentConfig().setAmount(residenceData.getAmount());
        }
        if(residenceData.getName() != null && !residenceData.getName().isBlank())  residenceFound.setName(residenceData.getName());
        if(residenceData.getAddress() != null && !residenceData.getAddress().isBlank())  residenceFound.setAddress(residenceData.getAddress());
       
        Residence residenceUpdated = this.residenceRepository.save(residenceFound);

        return residenceUpdated;
    }
    public Residence getResidenceById(Long id) {
        Optional<Residence> residenceFound = this.residenceRepository.findById(id); 
        if(residenceFound.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Residence not found");
        return residenceFound.get();
    }
  
    public Long delete(Long id){
        Residence residenceFinded =this.getResidenceById(id);
        this.residenceRepository.deleteById(residenceFinded.getId());

         return id;                       
        
        
      
    }


}
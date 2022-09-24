package com.residence.api.services;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.residence.api.models.House;
import com.residence.api.repositories.HouseRepository;

@Service

public class HouseService {
    
    @Autowired
    private HouseRepository houseRepository;


    public House getHouseById(Long id, Long residenceId) {
   
            
        House queryResponse = this.houseRepository.findHouseByIdAndResidenceId(id, residenceId);
        if(queryResponse == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "House not found");
        return queryResponse;
      
    }
        
    
}

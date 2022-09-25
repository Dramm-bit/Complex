package com.residence.api.services;




import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import com.residence.api.models.House;
import com.residence.api.models.Residence;
import com.residence.api.repositories.HouseRepository;
import com.residence.api.dataTranferObjects.HouseDTO;
import com.residence.api.repositories.ResidenceRepository;

@Service

public class HouseService {
    
    @Autowired
    private HouseRepository houseRepository;
    @Autowired
    private ResidenceRepository residenceRepository;

    public Optional<House> getHouseById(Long id, Long residenceId) {
   
            
       Optional<House> queryResponse = this.houseRepository.findHouseByIdAndResidenceId(id, residenceId);
        if(queryResponse.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "House not found");
        return queryResponse;
      
    }
    public House createHouse(HouseDTO houseData, Long residenceId) {
   
        House newHouse = new House();
        Optional<Residence> residenceFound = this.residenceRepository.findById(residenceId);

        if(residenceFound.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Residence not found");
        newHouse.setResidence(residenceFound.get());
        newHouse.setTower(houseData.getTower());
        newHouse = this.houseRepository.save(newHouse);
        
        return newHouse;
      
    }

    public Long findAndDelete(Long id, Long residenceId) {
   
        Optional<House> houseFound = this.getHouseById(id, residenceId);
        houseFound.ifPresent(house ->this.houseRepository.deleteById(house.getId()));

        return id;
      
    }

   public Optional <House> updateHouseBySpecificResidence (Long id, Long residenceId, HouseDTO houseData){
        Optional <House> updatedHouse = this.getHouseById(id, residenceId);
        updatedHouse.ifPresent(update -> {
            
            update.setTower(houseData.getTower());

        });

        return updatedHouse;
   }
        
    
}

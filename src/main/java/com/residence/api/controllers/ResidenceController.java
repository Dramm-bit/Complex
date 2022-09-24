package com.residence.api.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.residence.api.dataTranferObjects.HouseDTO;
import com.residence.api.dataTranferObjects.ResidenceDTO;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.residence.api.models.House;
import com.residence.api.models.Residence;
import com.residence.api.services.HouseService;
import com.residence.api.services.ResidenceService;

@RestController
@RequestMapping("/api/v1/residences")
public class ResidenceController {
    @Autowired
    private HouseService houseService;

    @Autowired
    private ResidenceService residenceService;

    @GetMapping("/{residence_id}/house/{id}")
    public ResponseEntity<Object> findHouseById(@PathVariable("residence_id") Long residenceId,
            @PathVariable("id") Long id) {

        House houseFound = this.houseService.getHouseById(id, residenceId);

        return ResponseEntity.ok().body(houseFound);
    }

    @PostMapping("/{residence_id}")
    public ResponseEntity<Object> createHouse(@RequestBody() HouseDTO houseData,
            @PathVariable("residence_id") Long residenceId) {

        House newHouse = this.houseService.createHouse(houseData, residenceId);

        return ResponseEntity.ok().body(newHouse);
    }

    @GetMapping()
    public ResponseEntity<Object> findResidences() {

        List<Residence> residencesFound = this.residenceService.getResidences();
        if (residencesFound.size() == 0)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Residences not found");
        return ResponseEntity.ok().body(residencesFound);
    }

    @DeleteMapping("/{residence_id}/house/{id}")
    public ResponseEntity<Object> findAndDeleteHouse(@PathVariable("residence_id") Long residenceId,
            @PathVariable("id") Long id) {

        Long houseIdDeleted = this.houseService.findAndDelete(id, residenceId);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "House with id " + houseIdDeleted + " deleted successfully");
        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    public ResponseEntity<Residence> createResidence(@RequestBody() ResidenceDTO residenceData) {

        Residence newResidence = this.residenceService.createResidence(residenceData);

        return ResponseEntity.ok().body(newResidence);

    }
    @GetMapping("/{residence_id}/house")
    public ResponseEntity<Set<House>> ListHousesByResidenceId (@PathVariable ("residence_id") Long residenceId){
        Set<House> finded = residenceService.findHousesinResidenceById(residenceId); 

        return ResponseEntity.ok().body(finded);
    }

    @DeleteMapping("/{residence_id}")
    public ResponseEntity<Object> deleteAllHousesByResidence(@PathVariable("residence_id") Long residenceId){
        this.residenceService.deleteAllHousesByResidence(residenceId);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Houses of residence with id " + residenceId + " deleted successfully");
        return ResponseEntity.ok().body(response);
        
    }

}

package com.residence.api.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.residence.api.dtos.HouseDTO;
import com.residence.api.models.House;
import com.residence.api.models.Residence;
import com.residence.api.services.HouseService;

@RestController
@RequestMapping("/api/v1/residences")
public class ResidenceController {
    @Autowired
   private final HouseService houseService;
   


    @GetMapping("/{residence_id}/house/{id}")
    public ResponseEntity<Object> findHouseById(@PathVariable("residence_id") Long residenceId,@PathVariable("id") Long id) {
      
        House houseFound = this.houseService.getHouseById(id, residenceId);
        
        return ResponseEntity.ok().body(houseFound);
    }

    @PostMapping("/{residence_id}")
    public ResponseEntity<Object> createHouse(@RequestBody() HouseDTO houseData, @PathVariable("residence_id") Long residenceId) {
      
        House newHouse = this.houseService.createHouse(houseData, residenceId);
        
        return ResponseEntity.ok().body(newHouse);
    }

    
    @PostMapping
public ResponseEntity<Residence> create(@Valid @RequestBody Residence residence){
    Residence newHouse = residenceService.create(residence);
   
    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(newHouse.getId())
        .toUri();
    return ResponseEntity.created(location).body(newHouse);
}
 
    
    

   
}
 
}

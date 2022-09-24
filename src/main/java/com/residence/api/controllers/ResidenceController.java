package com.residence.api.controllers;

import java.util.HashMap;
import java.util.Map;

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
import com.residence.api.services.HouseService;

@RestController
@RequestMapping("/api/v1/residences")
public class ResidenceController {
    @Autowired
    HouseService houseService;

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
}

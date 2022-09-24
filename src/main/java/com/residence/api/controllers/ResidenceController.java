package com.residence.api.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        Map<String, Object> response = new HashMap<>();
        if(houseFound == null) {
            response.put("message", "House with id " + id + " not found");
            response.put("status",  404);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        response.put("status", 200);
        response.put("message", "House found successfully");
        response.put("data", houseFound);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

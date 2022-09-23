package com.residence.api.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.residence.api.repositories.HouseRepository;

@Service

public class HouseService {
    
    @Autowired
    private HouseRepository houseRepository;



        
    
}

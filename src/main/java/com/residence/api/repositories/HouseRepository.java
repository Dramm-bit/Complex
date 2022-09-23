package com.residence.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.residence.api.models.House;

public interface HouseRepository extends JpaRepository<House, Long> {
    
}

package com.residence.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.residence.api.models.Residence;

public interface ResidenceRepository extends JpaRepository<Residence, Long>{
    
}

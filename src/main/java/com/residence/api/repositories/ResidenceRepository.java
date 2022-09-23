package com.residence.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.residence.api.models.Residence;
@Repository
public interface ResidenceRepository extends JpaRepository<Residence, Long>{
 
}

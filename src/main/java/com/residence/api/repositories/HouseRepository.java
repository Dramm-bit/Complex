package com.residence.api.repositories;




import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.residence.api.models.House;
@Repository

public interface HouseRepository extends JpaRepository<House, Long> {
    
    Optional<House> findHouseByIdAndResidenceId( Long id,Long residenceId);
   Set<House> findHouseByresidence_idIs(Long id);
}

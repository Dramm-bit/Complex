package com.residence.api.repositories;




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.residence.api.models.House;
@Repository

public interface HouseRepository extends JpaRepository<House, Long> {
    
    House findHouseByIdAndResidenceId( Long id,Long residenceId);
}

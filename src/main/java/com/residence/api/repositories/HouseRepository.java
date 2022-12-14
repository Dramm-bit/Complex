package com.residence.api.repositories;





import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.residence.api.models.House;
@Repository
public interface HouseRepository extends JpaRepository<House, Long> {
    
    House findHouseByIdAndResidenceId( Long id,Long residenceId);

    List<House> findHouseByResidenceIdIs(Long id);
}


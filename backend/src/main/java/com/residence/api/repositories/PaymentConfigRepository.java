package com.residence.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.residence.api.models.PaymentConfig;
@Repository
public interface PaymentConfigRepository extends JpaRepository<PaymentConfig,Long> {
    
    
        
    
}

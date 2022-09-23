package com.residence.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.residence.api.models.PaymentConfig;

public interface PaymentConfigRepository extends JpaRepository<PaymentConfig,Long> {
    
}

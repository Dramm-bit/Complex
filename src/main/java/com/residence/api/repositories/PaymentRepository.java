package com.residence.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.residence.api.models.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long>{
    
}

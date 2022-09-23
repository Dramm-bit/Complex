package com.residence.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.residence.api.models.Payment;
@Repository

public interface PaymentRepository extends JpaRepository<Payment, Long>{
    
}

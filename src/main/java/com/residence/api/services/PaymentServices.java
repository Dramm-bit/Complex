package com.residence.api.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.residence.api.repositories.PaymentRepository;

@Service
public class PaymentServices {
    @Autowired
    private PaymentRepository paymentRepository;
    

    
}

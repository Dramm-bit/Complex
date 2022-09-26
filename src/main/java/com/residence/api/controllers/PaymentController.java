package com.residence.api.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;



import org.springframework.web.bind.annotation.RestController;

import com.residence.api.dataTranferObjects.PaymentDTO;
import com.residence.api.services.PaymentService;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;


    @PutMapping("/residence/{residence_id}/house/{house_id}")
    public ResponseEntity<Object> createPayment(@PathVariable("residence_id") Long residenceId, @PathVariable("house_id") Long houseId, @RequestBody PaymentDTO paymentData) {

        Object paymentCreated = this.paymentService.createPayment(paymentData,residenceId, houseId);

        return ResponseEntity.status(201).body(paymentCreated);
    }   
}


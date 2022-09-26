package com.residence.api.services;



import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.residence.api.dataTranferObjects.PaymentDTO;
import com.residence.api.models.House;
import com.residence.api.models.Payment;
import com.residence.api.models.Residence;
import com.residence.api.repositories.HouseRepository;
import com.residence.api.repositories.PaymentRepository;
import com.residence.api.repositories.ResidenceRepository;



@Service
public class PaymentService {

    @Autowired
    HouseRepository houseRepository;
    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    ResidenceRepository residenceRepository;
    public Object createPayment(PaymentDTO paymentData,Long residenceId, Long houseId) {
        Optional<Residence> residenceFound = this.residenceRepository.findById(residenceId);
        if (residenceFound.isEmpty())
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Residence not found");

        Optional<House> houseFound = this.houseRepository.findById(houseId);
        if (houseFound.isEmpty())
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "House not found");

        try {
            Payment newPayment = new Payment();
            newPayment.setPaymentConfig(residenceFound.get().getPaymentConfig());
            newPayment.setHouse(houseFound.get());
            newPayment.setStatus(paymentData.getStatus());
            newPayment = this.paymentRepository.save(newPayment);
            Map<String,Object> responseData =  new HashMap<>();
            responseData.put("house", houseFound.get());
            responseData.put("residence", residenceFound.get());
            responseData.put("payment", newPayment);

            return responseData;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "There was an error trying create a payment");
        }



    }
    
}

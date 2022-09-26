package com.residence.api.models;


import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "payments")
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private StatePayment status;

    @ManyToOne()
    @JsonIgnore()
    @JoinColumn(name = "house_id")
    private House house;

    @ManyToOne()
    @JoinColumn(name = "payment_config_id")
    private PaymentConfig paymentConfig;

}

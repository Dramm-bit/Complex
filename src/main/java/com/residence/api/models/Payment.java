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

import lombok.Data;

@Entity
@Table(name = "payments")
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Enumerated(EnumType.STRING)
    private StatePayment status;

    @ManyToOne()
    @JoinColumn(name = "house_id")
    private House house;

    @ManyToOne()
    @JoinColumn(name = "payment_config_id")
    private PaymentConfig paymentConfig;

}

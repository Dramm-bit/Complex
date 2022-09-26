package com.residence.api.models;

import java.util.Set;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "payments_config")
@Data
public class PaymentConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @JsonIgnore()
    @OneToMany(mappedBy = "paymentConfig")
    private Set<Payment> payments;

    @JsonIgnore()
    @OneToOne(mappedBy = "paymentConfig")
    private Residence residence;

    private Double amount;
}

package com.residence.api.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "payments_config")
@Data
public class PaymentConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

}

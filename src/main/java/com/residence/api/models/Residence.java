package com.residence.api.models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;


@Entity
@Table(name = "residences")
@Data
public class Residence {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_config_id")
    private PaymentConfig paymentConfig;

    @JsonIgnore()
    @OneToMany(mappedBy = "residence",cascade = CascadeType.ALL)
    private Set<House> houses;

    private String name;

    
    
    
}

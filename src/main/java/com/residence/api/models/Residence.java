package com.residence.api.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "residences")
@Data
public class Residence {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

}

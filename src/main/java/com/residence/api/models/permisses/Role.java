package com.residence.api.models.permisses;



import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor

@Table(name="role")

public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Enumerated(EnumType.STRING)
    private TypeRole name;

    public Role(TypeRole name) {
        this.name = name;
    }

   


}

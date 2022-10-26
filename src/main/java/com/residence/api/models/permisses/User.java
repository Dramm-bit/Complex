package com.residence.api.models.permisses;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

// import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor

@Table(name="info_user", 
uniqueConstraints = { 
    @UniqueConstraint(columnNames = "username")})
                 


public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Email  
    @Size(max = 50)
    private String username; //el username sera tratado como el correo

    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "user_roles", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))

    private Set<Role> roles = new HashSet<>();

    public User(String username, String password) {
        this.username = username;
        this.password = password;
       
    }

}

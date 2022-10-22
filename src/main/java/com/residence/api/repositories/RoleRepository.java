package com.residence.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.residence.api.models.permisses.Role;
import com.residence.api.models.permisses.TypeRole;
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(TypeRole type);
    
}

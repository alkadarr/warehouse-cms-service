package com.radev.basecode.dao;

import com.radev.basecode.entity.ERole;
import com.radev.basecode.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
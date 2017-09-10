package com.security.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.security.auth.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	Role findByName(String roleName);
}

package com.security.auth.service;

import org.springframework.security.access.annotation.Secured;

import com.security.auth.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);

    @Secured("ROLE_ADMIN")
	String getAdminMessage();

	@Secured({"ROLE_USER"})
	String getUserMessage();
    
}

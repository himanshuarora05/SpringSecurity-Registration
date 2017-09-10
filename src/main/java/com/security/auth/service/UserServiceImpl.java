package com.security.auth.service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.security.auth.model.Role;
import com.security.auth.model.User;
import com.security.auth.repository.RoleRepository;
import com.security.auth.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	private static final String ROLE_NAME = "ROLE_USER";
	
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        System.out.println(user.getPassword());
        user.setRoles(getUserRole());
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
    private Set<Role> getUserRole() {
		return Stream.of(roleRepository.findByName(ROLE_NAME)).collect(Collectors.toSet());
	}
    
    @Override
    public String getAdminMessage() {
    	return "Admin Acess only";
    }
    
    @Override
    public String getUserMessage() {
    	return "General Acess";
    }
    
    
}

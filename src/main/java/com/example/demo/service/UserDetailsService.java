package com.example.demo.service;

import com.example.demo.dto.UserRequest;
import com.example.demo.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserDetailsService {
    UserDetails loadUserbyUsername(String username);
    User createUser(UserRequest userRequest);

    void deleteById(Long id);

//    Optional<User> findUserbyName(String name);
}

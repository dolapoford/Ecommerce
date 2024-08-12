package com.example.demo.service;

import com.example.demo.dto.UserRequest;
import com.example.demo.entity.User;
import com.example.demo.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;


    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserDetailsServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserbyUsername(String username) throws UsernameNotFoundException {
        User user  = userRepository.findUserByUsername(username).orElseThrow(()-> new UsernameNotFoundException("UserName Not Found "));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(user.getRole()))
        );
    }

    @Override
    public User createUser(UserRequest userRequest) {
        User user = User.builder()
                .username(userRequest.getUserName())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .role("ROLE_USER")
                .build();

        return userRepository.save(user);
    }

//    public Optional<User> findUserByUsername(String username) {
//        return userRepository.findUserbyName(username);
//    }

    @Override
    public void deleteById(Long id) {
     userRepository.deleteById(id);
    }

//    @Override
//    public Optional<User> findUserbyName(String name) {
//        return userRepository.findUserbyName(name);
//    }
}


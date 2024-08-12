package com.example.demo.rest;

import com.example.demo.dto.UserRequest;
import com.example.demo.entity.User;
import com.example.demo.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserDetailsService userDetailsService;
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRequest userRequest) {
        userDetailsService.createUser(userRequest);
        return ResponseEntity.ok("User registered successfully!");
    }

//    @GetMapping("/me")
//    public ResponseEntity<User> getCurrentUser(Principal principal) {
//        Optional<User> user = userDetailsService.findUserbyName(principal.getName());
//        return user.map(ResponseEntity::ok)
//                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
//    }
}

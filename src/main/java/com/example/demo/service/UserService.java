package com.example.demo.service;

import com.example.demo.dto.UserRequest;
import com.example.demo.entity.User;

public interface UserService {

    User createUser(UserRequest userRequest);

    void deleteById(Long id);

}

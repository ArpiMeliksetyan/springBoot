package com.example.demo.service.impl;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void update(User user) {
        User dbUser = userRepository.getByUsername(user.getUsername());

    }
}

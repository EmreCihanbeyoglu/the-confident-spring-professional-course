package com.cihanbeyoglu.service;


import com.cihanbeyoglu.model.User;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserService {

    public User findById(String id) {
        String randomName = UUID.randomUUID().toString();
        // always return a new user for given id
        return new User(id, randomName);
    }

}

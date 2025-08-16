package cihanbeyoglu.service;

import cihanbeyoglu.model.User;

import java.util.UUID;

public class UserService {

    public User findById(String id) {
        String randomName = UUID.randomUUID().toString();
        // always return a new user for given id
        return new User(id, randomName);
    }

}

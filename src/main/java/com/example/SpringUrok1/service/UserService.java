package com.example.SpringUrok1.service;

import com.example.SpringUrok1.models.User;

import java.util.List;

public interface UserService {
    List<User> getAll();

    User getById(int id);

    void deleteById(int id);

    User createUser(User user);

    User updateUser(int id,User user);

    void deleteAll();

    User findByUserName(String username);


}

package com.example.SpringUrok1.service.impl;

import com.example.SpringUrok1.exception.UserNotFound;
import com.example.SpringUrok1.models.User;
import com.example.SpringUrok1.repository.UserRepository;
import com.example.SpringUrok1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> getAll() {
        List<User> userModels = userRepository.findAll();
        return userModels;
    }

    @Override
    public User getById(int id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFound("User id not found : " + id));
        return user;
    }

    @Override
    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public User createUser(User user) {
        User users = userRepository.save(user);
        return users;
    }

    @Override
    public User updateUser(int id, User user) {
        User users = userRepository.findById(id).orElseThrow(() -> new UserNotFound("User id not found : " + id));
        if (users != null) {
            users.setFirstName(user.getFirstName());
            users.setPassword(user.getPassword());
            users.setPhoneNumber(user.getPhoneNumber());
            users.setUserName(user.getUserName());
        }
        User userfinals = userRepository.save(users);
        return userfinals;
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();

    }

    @Override
    public User findByUserName(String username) {
        User user=userRepository.findByUserName(username);
        return user;
    }
}

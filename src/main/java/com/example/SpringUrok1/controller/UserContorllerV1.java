package com.example.SpringUrok1.controller;

import com.example.SpringUrok1.models.User;
import com.example.SpringUrok1.service.UserService;
import com.example.SpringUrok1.service.impl.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/factory")
public class UserContorllerV1 {
    @Autowired
    UserServiceImp userServiceImp;

    @RequestMapping(value = "/allUsers", method = RequestMethod.GET)
    public ResponseEntity<User> allUsers() {
        List<User> userAll = userServiceImp.getAll();

        return new ResponseEntity(userAll, HttpStatus.OK);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> getById(@PathVariable(value = "id") int id) {
        User getUser = userServiceImp.getById(id);


        return new ResponseEntity(getUser, HttpStatus.OK);
    }


    @RequestMapping(value = "/users/create", method = RequestMethod.POST)
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User usersmodel = userServiceImp.createUser(user);
        if (usersmodel == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(usersmodel, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/users/deleted/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteUser(@PathVariable(value = "id") int id) {
        userServiceImp.deleteById(id);

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/users/deleted", method = RequestMethod.DELETE)
    public ResponseEntity deleteUsers() {
        userServiceImp.deleteAll();

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value ="/users/{id}",method = RequestMethod.PUT)
    public ResponseEntity<User> userUpdate(@PathVariable(value = "id") int id,@Valid @RequestBody User user){
        User userUpdate=userServiceImp.updateUser(id,user);

        return new ResponseEntity<>(userUpdate,HttpStatus.OK);
    }

    @RequestMapping(value="/users/username",method = RequestMethod.GET)
    public ResponseEntity<User> findByName(@PathVariable(value = "username") String username){
        User user=userServiceImp.findByUserName(username);
        if(user== null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(user,HttpStatus.OK);
    }
}

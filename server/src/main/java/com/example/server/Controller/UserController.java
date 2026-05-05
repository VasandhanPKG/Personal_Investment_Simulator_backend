package com.example.server.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.server.Service.UserService;
import com.example.server.Entity.UserEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;  
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/users")   
public class UserController {
    @Autowired
    private UserService userService;
    
    @GetMapping("/{id}" )
    public UserEntity getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/add")
    public UserEntity postMethodName(@RequestBody UserEntity entity) {
        return userService.addUser(entity);
    }

    @DeleteMapping("/{id}")
    public UserEntity deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }

    

    
    

}

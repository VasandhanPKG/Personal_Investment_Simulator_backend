package com.example.server.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.server.Entity.UserEntity;
import com.example.server.Repository.UserRepository;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

 
    public UserEntity addUser(UserEntity user) {
      
        if (user.getPrincipleAmount() < 0) {
            throw new IllegalArgumentException("Principal amount cannot be negative");
        }
        return userRepository.save(user);
    }

    public UserEntity getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

   
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

  
    public UserEntity updateUser(Long id, UserEntity updatedUser) {
        return userRepository.findById(id).map(user -> {
            if (updatedUser.getName() != null) {
                user.setName(updatedUser.getName());
            }
            if (updatedUser.getEmail() != null) {
                user.setEmail(updatedUser.getEmail());
            }
            if (updatedUser.getAge() > 0) {
                user.setAge(updatedUser.getAge());
            }
            if (updatedUser.getPrincipleAmount() >= 0) {
                user.setPrincipleAmount(updatedUser.getPrincipleAmount());
            }
            if (updatedUser.getBehaviorScore() != null) {
                user.setBehaviorScore(updatedUser.getBehaviorScore());
            }
            return userRepository.save(user);
        }).orElse(null);
    }

   
    public UserEntity deleteUser(Long id) {
        return userRepository.findById(id).map(user -> {
            userRepository.delete(user);
            return user;
        }).orElse(null);
    }


}

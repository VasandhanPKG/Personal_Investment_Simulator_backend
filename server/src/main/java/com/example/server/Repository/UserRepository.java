package com.example.server.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.server.Entity.UserEntity;
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    
}

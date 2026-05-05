package com.example.server.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.server.Entity.InvestmentEntity;

public interface InvestmentRepository extends JpaRepository<InvestmentEntity, Long> {
    
}

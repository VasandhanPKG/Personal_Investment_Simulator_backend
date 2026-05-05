package com.example.server.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.server.Entity.InvestmentEntity;
import com.example.server.Repository.InvestmentRepository;


@Service
public class InvestmentService {
    @Autowired
    private InvestmentRepository investmentRepository;
    public InvestmentEntity addInvestment(InvestmentEntity investment) {
        return investmentRepository.save(investment);
    }

    public InvestmentEntity getInvestmentById(Long id) {
        return investmentRepository.findById(id).orElse(null);
    }

    public InvestmentEntity deleteInvestment(Long id) {
        return investmentRepository.findById(id).map(investment -> {
            investmentRepository.delete(investment);
            return investment;
        }).orElse(null);
    }
    
        
}

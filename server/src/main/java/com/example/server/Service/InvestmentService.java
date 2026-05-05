package com.example.server.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.server.Entity.InvestmentEntity;

import com.example.server.Entity.RiskLevel;
import com.example.server.Repository.InvestmentRepository;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class InvestmentService {
    @Autowired
    private InvestmentRepository investmentRepository;

    
    public InvestmentEntity addInvestment(InvestmentEntity investment) {
        if (investment.getExpectedReturn() < 0) {
            throw new IllegalArgumentException("Expected return cannot be negative");
        }
        if (investment.getRiskLevel() == null) {
            throw new IllegalArgumentException("Risk level must be specified");
        }
        return investmentRepository.save(investment);
    }

    
    public InvestmentEntity getInvestmentById(Long id) {
        return investmentRepository.findById(id).orElse(null);
    }

    public List<InvestmentEntity> getAllInvestments() {
        return investmentRepository.findAll();
    }

   
    public List<InvestmentEntity> getInvestmentsByRiskLevel(RiskLevel riskLevel) {
        return investmentRepository.findAll().stream()
                .filter(inv -> inv.getRiskLevel() == riskLevel)
                .collect(Collectors.toList());
    }
    
    public List<InvestmentEntity> getLowRiskInvestments() {
        return getInvestmentsByRiskLevel(RiskLevel.LOW);
    }
    
    
    public List<InvestmentEntity> getMediumRiskInvestments() {
        return getInvestmentsByRiskLevel(RiskLevel.MEDIUM);
    }

    
    public List<InvestmentEntity> getHighRiskInvestments() {
        return getInvestmentsByRiskLevel(RiskLevel.HIGH);
    }

    
    public InvestmentEntity updateInvestment(Long id, InvestmentEntity updatedInvestment) {
        return investmentRepository.findById(id).map(investment -> {
            if (updatedInvestment.getExpectedReturn() >= 0) {
                investment.setExpectedReturn(updatedInvestment.getExpectedReturn());
            }
            if (updatedInvestment.getRiskLevel() != null) {
                investment.setRiskLevel(updatedInvestment.getRiskLevel());
            }
            if (updatedInvestment.getInvestmentType() != null) {
                investment.setInvestmentType(updatedInvestment.getInvestmentType());
            }
            return investmentRepository.save(investment);
        }).orElse(null);
    }

    
    public InvestmentEntity deleteInvestment(Long id) {
        return investmentRepository.findById(id).map(investment -> {
            investmentRepository.delete(investment);
            return investment;
        }).orElse(null);
    }

    
    

   

  
        
}

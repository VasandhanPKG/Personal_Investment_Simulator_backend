package com.example.server.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.server.Repository.*;

import com.example.server.Entity.*;
import java.util.*;
import java.util.stream.Collectors;




@Service
public class RecomendationService {
    @Autowired
    private RecomendationRepository recomendationRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private InvestmentRepository investmentRepository;

   
    public List<RecomendationEntity> getAllRecomendations() {
        return recomendationRepository.findAll();
    }

   
    public RecomendationEntity createRecomendation(RecomendationEntity recomendation) {
        if (recomendation.getUser() == null || recomendation.getInvestment() == null) {
            throw new IllegalArgumentException("User and Investment are required");
        }
        
        
        SuggestedAction action = determineAction(recomendation.getUser(), recomendation.getInvestment());
        recomendation.setSuggestedAction(action);
        
        return recomendationRepository.save(recomendation);
    }

   
    public List<RecomendationEntity> getRecommendationsByUserId(Long userId) {
        return recomendationRepository.findAll().stream()
                .filter(rec -> rec.getUser() != null && rec.getUser().getId().equals(userId))
                .collect(Collectors.toList());
    }

   
    public SuggestedAction determineAction(UserEntity user, InvestmentEntity investment) {
        
        if (user.getAge() < 30 && investment.getRiskLevel() == RiskLevel.HIGH) {
            return SuggestedAction.HOLD;
        }
        if (user.getBehaviorScore() == Behaviour.lossAversion) {
            return SuggestedAction.HOLD;
        }
        if (user.getPrincipleAmount() < 10000) {
            return SuggestedAction.HOLD;
        }
        if (investment.getRiskLevel() == RiskLevel.LOW && investment.getExpectedReturn() > 5) {
            return SuggestedAction.BUY;
        }
        if (investment.getRiskLevel() == RiskLevel.MEDIUM && user.getPrincipleAmount() >= 50000) {
            return SuggestedAction.BUY;
        }
        if (user.getBehaviorScore() == Behaviour.overconfidence && investment.getRiskLevel() == RiskLevel.HIGH) {
            return SuggestedAction.HOLD;
        }
        return SuggestedAction.HOLD;
    }

   
    public RecomendationEntity deleteRecomendation(Long id) {
        return recomendationRepository.findById(id).map(rec -> {
            recomendationRepository.delete(rec);
            return rec;
        }).orElse(null);
    }

    
    public List<RecomendationEntity> getRiskAppropriateRecommendations(Long userId) {
        UserEntity user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return List.of();
        }

        RiskLevel appropriateRisk = determineAppropriateRiskLevel(user);
        List<InvestmentEntity> investments = investmentRepository.findAll().stream()
                .filter(inv -> inv.getRiskLevel() == appropriateRisk)
                .collect(Collectors.toList());

        return investments.stream()
                .map(inv -> {
                    RecomendationEntity rec = new RecomendationEntity();
                    rec.setUser(user);
                    rec.setInvestment(inv);
                    rec.setSuggestedAction(determineAction(user, inv));
                    return rec;
                })
                .collect(Collectors.toList());
    }

    
    private RiskLevel determineAppropriateRiskLevel(UserEntity user) {
        if (user.getAge() < 25) {
            return RiskLevel.LOW;
        } else if (user.getAge() < 40) {
            return RiskLevel.MEDIUM;
        } else {
            return RiskLevel.LOW; 
        }
    }
}

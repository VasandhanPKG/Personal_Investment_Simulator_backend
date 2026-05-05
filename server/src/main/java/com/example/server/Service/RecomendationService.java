package com.example.server.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.server.Repository.RecomendationRepository;
import com.example.server.Entity.RecomendationEntity;
import java.util.List;


@Service
public class RecomendationService {
    @Autowired
    private RecomendationRepository recomendationRepository;

    public RecomendationEntity addRecomendation(RecomendationEntity recomendation) {
        return recomendationRepository.save(recomendation);
    }   

    public RecomendationEntity getRecomendationById(Long id) {
        return recomendationRepository.findById(id).orElse(null);
    }

    public List<RecomendationEntity> getallRecomendationbyUserId(Long id) {
        return recomendationRepository.findByUserId(id);
    }
    

    
}

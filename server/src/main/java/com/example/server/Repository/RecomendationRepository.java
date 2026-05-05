package com.example.server.Repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.server.Entity.RecomendationEntity;


public interface RecomendationRepository extends JpaRepository<RecomendationEntity, Long> {

    List<RecomendationEntity> findByUserId(Long id);

   
}
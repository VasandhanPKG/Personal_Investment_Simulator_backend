package com.example.server.Entity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class RecomendationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private UserEntity user;
    @OneToOne   
    private InvestmentEntity investment;
  
    private SuggestedAction suggestedAction;

}

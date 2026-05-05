package com.example.server.Entity;
import jakarta.persistence.*;
import lombok.Data;
import java.util.*;
@Entity
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int age;
    private String name;
    private String email;
    private int behaviorScore;
    private double  principleAmount;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<RecomendationEntity> recomendations;
}

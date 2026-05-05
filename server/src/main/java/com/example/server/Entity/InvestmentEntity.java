package com.example.server.Entity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class InvestmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private long id;
    private int riskScore;
    private double expectedReturn;
    private InvestmentType investmentType;
    

}

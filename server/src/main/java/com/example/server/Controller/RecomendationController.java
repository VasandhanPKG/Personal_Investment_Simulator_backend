package com.example.server.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.server.Service.RecomendationService;
import com.example.server.Entity.RecomendationEntity;
import java.util.List;


@RestController
@RequestMapping("/api/recommendations")
public class RecomendationController {
    @Autowired
    private RecomendationService recomendationService;

    @GetMapping
    public ResponseEntity<List<RecomendationEntity>> getAllRecomendations() {
        return ResponseEntity.ok(recomendationService.getAllRecomendations());
    }

    @PostMapping
    public ResponseEntity<RecomendationEntity> createRecomendation(@RequestBody RecomendationEntity recomendation) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(recomendationService.createRecomendation(recomendation));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<RecomendationEntity>> getRecommendationsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(recomendationService.getRecommendationsByUserId(userId));
    }

  

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecomendation(@PathVariable Long id) {
        RecomendationEntity deleted = recomendationService.deleteRecomendation(id);
        if (deleted != null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/user/{userId}/risk-appropriate")
    public ResponseEntity<List<RecomendationEntity>> getRiskAppropriateRecommendations(@PathVariable Long userId) {
        return ResponseEntity.ok(recomendationService.getRiskAppropriateRecommendations(userId));
    }
}

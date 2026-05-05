package com.example.server.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.server.Service.InvestmentService;
import com.example.server.Entity.InvestmentEntity;
import com.example.server.Entity.RiskLevel;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/investments")
public class InvestmentController {
    @Autowired
    private InvestmentService investmentService;

    @GetMapping("/{id}")
    public ResponseEntity<InvestmentEntity> getInvestmentById(@PathVariable Long id) {
        InvestmentEntity investment = investmentService.getInvestmentById(id);
        if (investment != null) {
            return ResponseEntity.ok(investment);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping
    public ResponseEntity<List<InvestmentEntity>> getAllInvestments() {
        return ResponseEntity.ok(investmentService.getAllInvestments());
    }

    @PostMapping
    public ResponseEntity<InvestmentEntity> addInvestment(@RequestBody InvestmentEntity investment) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(investmentService.addInvestment(investment));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<InvestmentEntity> updateInvestment(@PathVariable Long id, @RequestBody InvestmentEntity investment) {
        InvestmentEntity updated = investmentService.updateInvestment(id, investment);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvestment(@PathVariable Long id) {
        InvestmentEntity deleted = investmentService.deleteInvestment(id);
        if (deleted != null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/risk/low")
    public ResponseEntity<List<InvestmentEntity>> getLowRiskInvestments() {
        return ResponseEntity.ok(investmentService.getLowRiskInvestments());
    }

    @GetMapping("/risk/medium")
    public ResponseEntity<List<InvestmentEntity>> getMediumRiskInvestments() {
        return ResponseEntity.ok(investmentService.getMediumRiskInvestments());
    }

    @GetMapping("/risk/high")
    public ResponseEntity<List<InvestmentEntity>> getHighRiskInvestments() {
        return ResponseEntity.ok(investmentService.getHighRiskInvestments());
    }

    @GetMapping("/filter")
    public ResponseEntity<List<InvestmentEntity>> getInvestmentsByRiskLevel(@RequestParam RiskLevel riskLevel) {
        return ResponseEntity.ok(investmentService.getInvestmentsByRiskLevel(riskLevel));
    }
}

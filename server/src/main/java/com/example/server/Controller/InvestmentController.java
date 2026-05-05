package com.example.server.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.server.Service.InvestmentService;
import com.example.server.Entity.InvestmentEntity;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/investments")
public class InvestmentController {
    @Autowired
    private InvestmentService investmentService;
    @GetMapping("/{id}" )
    public InvestmentEntity getInvestmentById(@PathVariable Long id) {
        return investmentService.getInvestmentById(id);
    }

    @PostMapping("/add" )
    public InvestmentEntity addInvestment(@RequestBody InvestmentEntity entity) {
        return investmentService.addInvestment(entity);
    }

    @DeleteMapping("/{id}")
    public InvestmentEntity deleteInvestment(@PathVariable Long id) {
        return investmentService.deleteInvestment(id);
    }
    
}

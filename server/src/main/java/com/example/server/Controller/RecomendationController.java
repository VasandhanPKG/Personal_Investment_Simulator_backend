package com.example.server.Controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import com.example.server.Service.RecomendationService;
import com.example.server.Entity.RecomendationEntity;
import java.util.List;


@RestController
@RequestMapping("/api/recomendations")
public class RecomendationController {
    @Autowired
    private RecomendationService recomendationService;

    @GetMapping("/{id}" )
    public List<RecomendationEntity> getRecomendationById(@PathVariable Long id) {
        return recomendationService.getallRecomendationbyUserId(id);
    }

    @PostMapping("/add" )
    public RecomendationEntity addRecomendation(@RequestBody RecomendationEntity entity)
    {
        return recomendationService.addRecomendation(entity);
    }

    

    

}

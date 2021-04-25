package com.geraud.competitionbloc.controllers;


import com.geraud.competitionbloc.models.Category;
import com.geraud.competitionbloc.models.Competition;
import com.geraud.competitionbloc.repositories.CategoryRepository;
import com.geraud.competitionbloc.repositories.CompetitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;

@RestController()
@RequestMapping("/competition")
public class CompetitionCreationController {


    CompetitionRepository competitionRepository;
    CategoryRepository categoryRepository;

    public CompetitionCreationController(CompetitionRepository competitionRepository, CategoryRepository categoryRepository) {
        this.competitionRepository = competitionRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/all")
    public Flux<Competition> getAllCompetitions(){
        return competitionRepository.findAll();
    }

    @PostMapping("/creation")
    public Mono<ResponseEntity<?>> createCompetition(@RequestBody  Competition competition) {
        return this.competitionRepository.save(competition)
                .map(competition1 -> ResponseEntity.created(URI.create("/competition/all")).build());
    }

    @PostMapping("/categories")
    public Mono<ResponseEntity<?>> createCategories(@RequestBody List<Category> categories){
        try {
            categories.forEach(c ->categoryRepository.save(c).subscribe());

        }catch (Exception e){
            return  Mono.just(ResponseEntity.status(HttpStatus.NOT_MODIFIED).build());
        }
        return Mono.just(ResponseEntity.status(201).build());
    }
}

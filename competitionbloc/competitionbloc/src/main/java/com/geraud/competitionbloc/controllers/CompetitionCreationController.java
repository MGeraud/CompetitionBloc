package com.geraud.competitionbloc.controllers;


import com.geraud.competitionbloc.models.Category;
import com.geraud.competitionbloc.models.Competition;
import com.geraud.competitionbloc.repositories.CategoryRepository;
import com.geraud.competitionbloc.repositories.CompetitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;

@RestController()
@CrossOrigin
@RequestMapping("/competition")
public class CompetitionCreationController {


    CompetitionRepository competitionRepository;
    CategoryRepository categoryRepository;
    ReactiveMongoTemplate reactiveMongoTemplate;

    public CompetitionCreationController(CompetitionRepository competitionRepository, CategoryRepository categoryRepository,ReactiveMongoTemplate reactiveMongoTemplate) {
        this.competitionRepository = competitionRepository;
        this.categoryRepository = categoryRepository;
        this.reactiveMongoTemplate=reactiveMongoTemplate;
    }

    /**
     * endpoint pour charger les différentes compétitions présente en base de donnée
     * @return flux de toutes les compétitions présentes en base de données
     */
    @GetMapping("/all")
    public Flux<Competition> getAllCompetitions(){
        return competitionRepository.findAll();
    }


    /**
     * Endpoint permetant la création d'une nouvelle compétition, il créé en même temps les catégories précisées dans le formulaire
     * @param competition : identification de la compétition (nom, année , catégories)
     * @return statut http en fonction de la réussite ou non de l'enregistrement
     */
    @PostMapping("/creation")
    public ResponseEntity<?> createCompetition(@RequestBody  Competition competition) {

        try {
            competition.getCategories().forEach(
                    category ->
                            categoryRepository.save(new Category(category.toLowerCase(), competition.getCompetitionName() + " " + competition.getYear()))
                    .subscribe()
            );
            competitionRepository.save(competition)
            .subscribe();
        } catch (Exception e) {
            return  ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
        }
        return ResponseEntity.status(201).build();
    }

    @PostMapping("/add")
    public Mono<Category> addboulder (@RequestBody Category category) {
        return categoryRepository.addNewBoulder(category);
    }

    @DeleteMapping("/delete")
    public Mono<Category> deleteBoulder(@RequestBody Category category) {
         return categoryRepository.deleteBoulder(category);
    }
}

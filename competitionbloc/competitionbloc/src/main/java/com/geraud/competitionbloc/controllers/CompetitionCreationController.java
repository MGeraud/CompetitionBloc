package com.geraud.competitionbloc.controllers;


import com.geraud.competitionbloc.models.Category;
import com.geraud.competitionbloc.models.Competition;
import com.geraud.competitionbloc.service.CategoryService;
import com.geraud.competitionbloc.service.CompetitionService;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController()
@CrossOrigin
@RequestMapping("/competition")
public class CompetitionCreationController {

    ReactiveMongoTemplate reactiveMongoTemplate;
    CategoryService categoryService;
    CompetitionService competitionService;

    public CompetitionCreationController(CompetitionService competitionService,  ReactiveMongoTemplate reactiveMongoTemplate, CategoryService categoryService) {
        this.competitionService = competitionService;
        this.reactiveMongoTemplate = reactiveMongoTemplate;
        this.categoryService = categoryService;
    }

    /**
     * endpoint pour charger les différentes compétitions présente en base de donnée
     * @return flux de toutes les compétitions présentes en base de données
     */
    @GetMapping("/all")
    public Flux<Competition> getAllCompetitions(){
        return competitionService.listAll();
    }


    /**
     * Endpoint permetant la création d'une nouvelle compétition, il créé en même temps les catégories précisées dans le formulaire
     * @param competition : identification de la compétition (nom, année , catégories)
     * @return statut http en fonction de la réussite ou non de l'enregistrement
     */
    @PostMapping("/creation")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Competition> createCompetition(@RequestBody  Competition competition) {

            categoryService.createCategories(competition);
            return competitionService.saveCompetition(competition);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Category> addboulder (@RequestBody Category category) {
        return categoryService.addBoulder(category);
    }

    @DeleteMapping("/delete")
    public Mono<Category> deleteBoulder(@RequestBody Category category) {
         return categoryService.deleteBoulder(category);
    }
}

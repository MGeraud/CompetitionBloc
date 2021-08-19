package com.geraud.competitionbloc.controllers;


import com.geraud.competitionbloc.models.Category;
import com.geraud.competitionbloc.models.Competition;
import com.geraud.competitionbloc.service.CategoryService;
import com.geraud.competitionbloc.service.CompetitionService;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController()
@CrossOrigin
@RequestMapping("/competition")
public class CompetitionCreationController {


    CategoryService categoryService;
    CompetitionService competitionService;

    public CompetitionCreationController(CompetitionService competitionService,  CategoryService categoryService) {
        this.competitionService = competitionService;
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

            return competitionService.saveCompetition(competition);
    }

    /**
     * Endpoint permetant la création ou l'ajout de blocs pour une compétition donnée
     * @param category categorie à laquelle sdoit être ajouté le(s) bloc(s)
     * @return catégorie modifiée
     */
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Category> addboulder (@RequestBody Category category) {
        return categoryService.addBoulder(category);
    }

    /**
     * Endpoint permetant le retrait de blocs pour une compétition donnée
     * @param category categorie à laquelle doit être retiré le(s) bloc(s)
     * @return catégorie modifiée
     */
    @DeleteMapping("/delete")
    public Mono<Category> deleteBoulder(@RequestBody Category category) {
         return categoryService.deleteBoulder(category);
    }
}

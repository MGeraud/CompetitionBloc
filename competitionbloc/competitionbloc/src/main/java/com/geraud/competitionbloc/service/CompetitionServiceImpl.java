package com.geraud.competitionbloc.service;

import com.geraud.competitionbloc.models.Competition;
import com.geraud.competitionbloc.repositories.CompetitionRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CompetitionServiceImpl implements CompetitionService{

    CompetitionRepository competitionRepository;
    CategoryService categoryService;


    public CompetitionServiceImpl(CompetitionRepository competitionRepository, CategoryService categoryService) {
        this.competitionRepository = competitionRepository;
        this.categoryService=categoryService;
    }

    @Override
    public Flux<Competition> listAll() {
        return competitionRepository.findAll();
    }

    /**
     * Création de la compétition en BDD aisni que la création des différentes catégories de celle-ci
     * @param competition à créer en BDD
     * @return compétition créée
     */
    @Override
    public Mono<Competition> saveCompetition(Competition competition) {
        categoryService.createCategories(competition);
        return competitionRepository.save(competition);
    }
}

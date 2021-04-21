package com.geraud.competitionbloc.controllers;


import com.geraud.competitionbloc.models.Competition;
import com.geraud.competitionbloc.repositories.CompetitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController()
@RequestMapping("/competition")
public class CompetitionCreationController {


    CompetitionRepository competitionRepository;

    public CompetitionCreationController(CompetitionRepository competitionRepository) {
        this.competitionRepository = competitionRepository;
    }

    @GetMapping("/all")
    public Flux<Competition> getAllCompetitions(){
        return competitionRepository.findAll();
    }
}

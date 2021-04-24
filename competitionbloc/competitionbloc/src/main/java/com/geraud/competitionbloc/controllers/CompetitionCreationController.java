package com.geraud.competitionbloc.controllers;


import com.geraud.competitionbloc.models.Competition;
import com.geraud.competitionbloc.repositories.CompetitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

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

    @PostMapping("/creation")
    public Mono<ResponseEntity<?>> createCompetition(@RequestBody  Competition competition) {
        return this.competitionRepository.save(competition)
                .map(competition1 -> ResponseEntity.created(URI.create("/competition/all")).build());
    }
}

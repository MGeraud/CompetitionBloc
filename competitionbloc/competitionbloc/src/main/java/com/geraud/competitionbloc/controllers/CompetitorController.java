package com.geraud.competitionbloc.controllers;

import com.geraud.competitionbloc.models.Competitor;
import com.geraud.competitionbloc.repositories.CompetitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class CompetitorController {

    @Autowired
    CompetitorRepository repository;

    @GetMapping("/{id}")
    public Mono<Competitor> selectedCompetitor(@PathVariable String id){
//        Mono<Competitor> competitor = repository.findById(id);

        return selectedCompetitor(id);
    }

    @GetMapping("/")
    public Flux<Competitor> allCompetitors(){
        return repository.findAll();
    }
}
